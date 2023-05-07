import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllTemplateUserComponent } from './FrontOffice/all-template-user/all-template-user.component';
import { BodyUserComponent } from './FrontOffice/body-user/body-user.component';
import { AllTemplateAdminComponent } from './BackOffice/all-template-admin/all-template-admin.component';
import { BodyAdminComponent } from './BackOffice/body-admin/body-admin.component';
import { LoginComponent } from './FrontOffice/login/login.component';
import { RegisterComponent } from './FrontOffice/register/register.component';
import { HomeComponent } from './FrontOffice/home/home.component';
import { BoardUserComponent } from './FrontOffice/board-user/board-user.component';
import { BoardWorkerComponent } from './FrontOffice/board-worker/board-worker.component';
import { BoardAdminComponent } from './BackOffice/board-admin/board-admin.component';
import { ProfileComponent } from './profile/profile.component';
import { UserDisplayComponent } from './BackOffice/userCrud/user-display/user-display.component';
import { UserCreateComponent } from './BackOffice/userCrud/user-create/user-create.component';
import { UserUpdateComponent } from './BackOffice/userCrud/user-update/user-update.component';
import { ForgetPasswordComponent } from './FrontOffice/forget-password/forget-password.component';
import { NotFoundComponentComponent } from './FrontOffice/not-found-component/not-found-component.component';
import { ReservationComponent } from './FrontOffice/reservation/reservation.component';
import { ProfileListComponentComponent } from './profile-list-component/profile-list-component.component';
import { ProfileFrontComponent } from './profile-front/profile-front.component';
import { NotificationPopupComponent } from './notification-popup/notification-popup.component';
import { AllnotComponent } from './BackOffice/allnot/allnot.component';
import { AllresComponent } from './BackOffice/allres/allres.component';
import { ChatComponent } from './FrontOffice/chat-message/chat-message.component';
import { EventListComponent } from './FrontOffice/event-list/event-list.component';
import { FormationComponent } from './FrontOffice/formation/formation.component';
import { FormationListComponent } from './FrontOffice/formation-list/formation-list.component';
import { EvenementComponent } from './FrontOffice/evenement/evenement.component';
import { EvenementBackendComponent } from './FrontOffice/evenement-backend/evenement-backend.component';
import { FormationBackComponent } from './FrontOffice/formation-back/formation-back.component';
import { AddPublicationComponent } from './add-publication/add-publication.component';


const routes: Routes = [
  {
    path: 'user',
    component: AllTemplateUserComponent,
    children: [
      { path: 'home', component: BodyUserComponent },
      { path: 'publication', component: AddPublicationComponent },
      { path: 'UpdateAccount', component: UserUpdateComponent },
      { path: 'profile/add-profile', component: ProfileComponent },
      { path: 'profile/display', component: ProfileFrontComponent },
      { path: 'reservation', component: ReservationComponent },
      { path: 'not', component: NotificationPopupComponent },
      { path: 'chat', component: ChatComponent },
      { path: 'eventlist', component: EventListComponent },
      { path: 'formationAdd', component: FormationComponent },
      { path: 'formationlist', component: FormationListComponent },
      { path: 'evenement', component: EvenementComponent },
      { path: 'allres', component: AllresComponent },
      { path: '', redirectTo: 'home', pathMatch: 'full' },
    ],
  },
  {
    path: 'admin',
    component: AllTemplateAdminComponent,
    children: [
      { path: 'home', component: BodyAdminComponent },
      { path: 'allres', component: AllresComponent },
    ],
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'logout', redirectTo: 'user/home', pathMatch: 'full' },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'mod', component: BoardWorkerComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'usersList', component: UserDisplayComponent },
  { path: 'usersCreate', component: UserCreateComponent },
  { path: 'usersUpdate', component: UserUpdateComponent },
  { path: 'ForgotPassword', component: ForgetPasswordComponent },
  { path: 'profile/update-profile', component: ProfileListComponentComponent },
  { path: 'allnot', component: AllnotComponent },
  { path: 'evenementbackend', component: EvenementBackendComponent },
  { path: 'formationbackend', component: FormationBackComponent },
  { path: '**', component: NotFoundComponentComponent },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
