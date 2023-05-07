import { LocalDate } from "@js-joda/core";
import { Publication } from "./publication";

export class Commentaire {
    idComm!: number;
    contenuComm!: string;
    likeCount!: number;
    dislikeCount!: number;
    dateCreationComm!: LocalDate;
    publication!: Publication;
    user!: any;
    like?: any[];
  }
  