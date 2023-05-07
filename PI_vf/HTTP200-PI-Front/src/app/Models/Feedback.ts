import { Profile } from "./Profile";

export class Feedback {
  id!: number;
  message!: string;
  rating!: number;
  createdDate!: Date;
  updatedDate!: Date;
  profile: Profile = new Profile;
}
