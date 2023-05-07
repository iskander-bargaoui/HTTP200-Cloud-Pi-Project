export class User {
    id!: number;
    nom!: String;
    prenom!: String;
    email!: String;
    username!: String;
    password!: String;
    phoneNumber!: number;
    //address!: String;
    //enabled!:boolean;
   // role!:any[];
    birthDate:Date = new Date;
    constructor(){
    }
}