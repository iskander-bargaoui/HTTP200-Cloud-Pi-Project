import { Commentaire } from "./commentaire";
import { LikeType } from "./likeType";
import { Publication } from "./publication";
export class Like {

idlike!: number;
likeType!: LikeType;
user?: any;
publication!: Publication;
commentaire!: Commentaire;
  
}