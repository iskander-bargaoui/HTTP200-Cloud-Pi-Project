import { Evenement } from "./evenement";

export class Formation {
    idFormation?: number;
    titreFormation?: string;
    dateDebutFor?: Date;
    dateFinFor?: Date;
    description?: string;
    organisateur?: string;
    event: Evenement = new Evenement();  
 }