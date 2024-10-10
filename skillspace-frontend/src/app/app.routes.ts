import { Routes } from '@angular/router';
import { AuthComponent } from './views/auth/auth.component';
import { HomeComponent } from './views/home/home.component';
import { ApplicationTrackingComponent } from './features/talent-dashboard-features/application-tracking/application-tracking.component';
import { ProgramCreationComponent } from './features/company/program-creation/program-creation.component';
import { CareerProgramsComponent } from './features/company/career-programs/career-programs.component';

export const routes: Routes = [
  {path: '', component: ApplicationTrackingComponent},
  {
    path: 'auth',
    component: AuthComponent,
    children: [
      {
        path: 'login',
        loadComponent: ()=>import('./features/auth/login-form/login-form.component').then(m=>m.LoginFormComponent),
      },
      {
        path: 'register',
        loadComponent: ()=>import('./features/auth/registration/components/registration/registration.component').then(m=>m.RegistrationComponent),
      },
      {
        path: 'password-reset',
        loadComponent: ()=>import('./features/auth/password-reset/password-reset.component').then((m)=>m.PasswordResetComponent)
      },

      {
        path: 'verification',
        loadComponent: ()=>import('./features/auth/verification/verification.component').then((m)=>m.VerificationComponent)
      }

    ]
  },
  {
    path:'home-talent',
    component: HomeComponent,
    children:[
        {
            path: '',
            loadComponent: ()=>import('./features/talent-dashboard-features/career-program-list/career-program-application.component').then(m=>m.CareerProgramApplicationComponent),
        },
        {
            path: 'career-programs',
            loadComponent: ()=>import('./features/talent-dashboard-features/career-program-list/career-program-application.component').then(m=>m.CareerProgramApplicationComponent),
        },
        {
            path: 'program-detail',
            loadComponent: ()=>import('./features/talent-dashboard-features/progam-detail/progam-detail.component').then(m=>m.ProgamDetailComponent),
        },
        {
            path: 'applications',
            loadComponent: ()=>import('./features/talent-dashboard-features/apply-to-program/apply-to-program.component').then(m=>m.ApplyToProgramComponent),
        },
        {
            path: 'assessments',
            loadComponent: ()=>import('./features/talent-dashboard-features/career-program-list/career-program-application.component').then(m=>m.CareerProgramApplicationComponent),
        },
        {
            path: 'messages',
            loadComponent: ()=>import('./features/talent-dashboard-features/career-program-list/career-program-application.component').then(m=>m.CareerProgramApplicationComponent),
        },
        {
            path: 'settings',
            loadComponent: ()=>import('./features/profile-management/profile-management.component').then(m=>m.ProfileManagementComponent),
        },

    ]
  },
  {
    path:'company',
    component: HomeComponent,
    children:[
      {
        path: 'programs',
        component: CareerProgramsComponent,
        children: [
          {
            path: '',
            redirectTo: 'create',
            pathMatch: 'full'
          },
          {
            path: 'create',
            component: ProgramCreationComponent
          },
          {
            path: 'drafts',
            loadComponent: () => import('./features/company/drafted-programs/drafted-programs.component')
              .then(m => m.DraftedProgramsComponent),
          },
          {
            path: 'published',
            loadComponent: () => import('./features/company/published-programs/published-programs.component')
              .then(m => m.PublishedProgramsComponent),
          }
        ]
      }
    ]
  },

  {
    path: '**',
    redirectTo: '/auth/login',
    pathMatch: 'full',
  }

];
