import { Feedback } from "./Feedback";

export class Profile {
  id!: number;
  username!: string;
  sexe!: string;
  categorie!: Categorie;
  isVerified: boolean = false;
  photoprofile!: string;
  feedbackList: Feedback[] = [];
}

export enum Categorie {
    Plombier = "plombier",
    Electricien = "electricien",
    Jardinier = "jardinier"
  }
