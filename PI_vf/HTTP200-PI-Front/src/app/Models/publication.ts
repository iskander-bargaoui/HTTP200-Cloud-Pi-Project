import { Visibilite } from "./visibilite";
import { LocalDateTime,LocalDate } from '@js-joda/core';

export class Publication {
    idPub!: number;
    contenuPub!: string;
    titrePub!: string;
    vis!: Visibilite;
    dateCreationPub!: LocalDate ;
    //image!:ArrayBuffer;
    //imageBase64!:String;
    image!:string;
    likeCount!: number;
    dislikeCount!: number ;
    isFavorite!: boolean ;
    favoriteDate!: LocalDateTime ;
    user?: any;
    commentaire: any[] = [];
    likes: any[] = [];
    favoriteUsers?: any[];
  }
  