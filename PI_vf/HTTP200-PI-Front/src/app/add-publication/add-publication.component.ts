import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Publication } from '../Models/publication';
import { PublicationService } from '../Services/publication.service';
import { Visibilite } from '../Models/visibilite';
import { HttpClient } from '@angular/common/http';
import { Modal } from 'bootstrap';
import { Router } from '@angular/router';
import { LocalDate, LocalDateTime } from '@js-joda/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommentaireService } from '../Services/commentaire.service';
import { Commentaire } from '../Models/commentaire';
import { Observable, tap } from 'rxjs';
import { LikeService } from '../Services/like.service';
import { Like } from '../Models/like';
import { LikeType } from '../Models/likeType';

import * as $ from 'jquery';
import { StorageService } from '../FrontOffice/Services/storage.service';



@Component({
  selector: 'app-add-publication',
  templateUrl: './add-publication.component.html',
  styleUrls: ['./add-publication.component.css']
})
export class AddPublicationComponent implements OnInit {

  


  publications: Publication[] = [] ;
  commentaires: Commentaire[] = [] ;
  

  newPublication: any = {};
  image!: string;

  selectedPublication!: Publication;
  selectedCommentaire!: Commentaire;


  publication: Publication = new Publication();
  commentaire: Commentaire = new Commentaire();

  //submitted = false;
  selectedFile!: File;
  newCommentaire: Commentaire = new Commentaire();

  commentCount!: number;


  private baseUrl = 'http://localhost:8080/api/commentaire';
  contenuComm: any;

  likeType!: LikeType  // Default value is LIKE

  favoritePublications!: Publication[];


  constructor(private storageService: StorageService,private likeService: LikeService,private commentaireService: CommentaireService,private publicationService: PublicationService,private http:HttpClient, private router: Router,private modalService: NgbModal) {
   
   }

  ngOnInit(): void {
    this.getPublications();
  }

   onAddPublication(publication: Publication): void {
     const userId = 1; // replace with the actual user ID
     publication.dateCreationPub = LocalDate.now(); // set the date to the current date
     publication.dislikeCount=0;
     publication.likeCount=0;
     publication.isFavorite=false;
     
     this.publicationService.addPublication(publication)
       .subscribe(newPublication => {
         this.newPublication = new Publication();
         $('#exampleModalCenter').modal('hide')

       });
   }  

   addComment(idPub: number) {
    // assign the new comment to the publication and post it to the server
    const userId = 1;
    this.newCommentaire.likeCount = 0;
    this.newCommentaire.dislikeCount = 0;
    this.newCommentaire.dateCreationComm = LocalDate.now(); 
    this.newCommentaire.contenuComm = this.contenuComm;

    this.commentaireService.assignCommentaireToPub(this.newCommentaire, idPub).subscribe(
      comment => {
        this.publication.commentaire.push(comment);
        console.log(comment);
        
        // reset the new comment object
        this.newCommentaire = new Commentaire();
        this.contenuComm = ''; // clear the input field

      },
      error => console.log(error)
    );
  }
  

  

   /*onUpdatePublication(publication: Publication): void {
    const userId = 1; // replace with the actual user ID
    this.publicationService.updatePublication(publication.idPub, publication)
      .subscribe(() => {
        const index = this.publications.findIndex(p => p.idPub === publication.idPub);
        if (index !== -1) {
          this.publications[index] = publication;
        }
      });
      $('#updatePublicationModal').modal('hide'); // hide the modal after the update is successful

  }*/

  updatePublication(publication: Publication): void {
    const currentUserId = this.storageService.getUser().id;
     

  // check if the current user is the owner of the publicatio

    this.publicationService.updatePublication(publication.idPub, publication).subscribe(
      () => {
        console.log('Publication updated successfully.');
        // do something else, like navigate to a different page or update the UI
      },
      (error) => {
        console.error('Error updating publication:', error);
        // handle the error, such as displaying an error message to the user
      }
    );
  }
  

  updateComment(commentaire: Commentaire): void {
    this.commentaireService.updateCommentaire(commentaire.idComm, commentaire).subscribe(
      () => {
        console.log('Commentaire updated successfully.');
        // do something else, like navigate to a different page or update the UI
      },
      (error) => {
        console.error('Error updating Commentaire:', error);
        // handle the error, such as displaying an error message to the user
      }
    );
  }
  
  
  
  selectPublication(publication: any) {
    this.selectedPublication = publication;
  }

  selectCommentaire(commentaire: any) {
    this.selectedCommentaire = commentaire;
  }

  

 /* onAddPublication() {
    const userId = 1; // replace with the actual user ID
    this.publication.dateCreationPub = LocalDate.now();
    this.publication.favoriteDate = LocalDateTime.now(); // set the date to the current date
    this.publication.dislikeCount = 0;
    this.publication.likeCount = 0;
    this.publication.isFavorite = false;
    this.publicationService.addPub(this.publication.titrePub, this.publication.contenuPub, this.publication.vis, this.publication.dateCreationPub, this.imageFile, this.publication.likeCount, this.publication.dislikeCount, this.publication.isFavorite, this.publication.favoriteDate, userId)
      .subscribe(data => console.log(data), error => console.log(error));
    this.publication = new Publication();
    $('#exampleModalCenter').modal('hide')


  }*/

  /*selectPublication(publication: any) {
    this.selectPublication = publication;
  }*/

  /*onSubmit() {
    this.submitted = true;
    this.onAddPublication();
  }*/

 /* onAddPublication(publication: Publication): void {
    const userId = 1; // replace with the actual user ID
    publication.dateCreationPub = LocalDate.now(); // set the date to the current date
    publication.dislikeCount=0;
    publication.likeCount=0;
    publication.isFavorite=false;
    
    this.publicationService.addPublication(publication, userId)
      .subscribe(newPublication => {
        this.newPublication = new Publication();
      });
  }  */

  
  onSubmit(event: Event) {
    const fileInput = event.target as HTMLInputElement;
    if (!fileInput.files || fileInput.files.length === 0) {
      console.log('No file selected');
      return;
    }
    this.selectedFile = fileInput.files[0];
    this.publication.image = "/assets/images/"+this.selectedFile.name;
    console.log(this.selectedFile.name);
    const formData = new FormData();
    formData.append('file', fileInput.files[0]);
    this.http.post<any>('http://localhost:8080/api/publications/uploadImage', formData).subscribe(
      (res) => {
        this.image = res.imagePath;
        console.log(this.image);
      },
      (err) => {
        console.log(err);
      } 
    );
  }

  onSubmitU(event: Event) {
    const fileInput = event.target as HTMLInputElement;
    if (!fileInput.files || fileInput.files.length === 0) {
      console.log('No file selected');
      return;
    }
    this.selectedFile = fileInput.files[0];
    this.selectedPublication.image = "/assets/images/"+this.selectedFile.name;
    console.log(this.selectedFile.name);
    const formData = new FormData();
    formData.append('file', fileInput.files[0]);
    this.http.post<any>('http://localhost:8080/api/publications/uploadImage', formData).subscribe(
      (res) => {
        this.image = res.imagePath;
        console.log(this.image);
      },
      (err) => {
        console.log(err);
      } 
    );
  }


  getPublications(): void {
    this.publicationService.getAllPublications().subscribe(
      data => {
        this.publications = data;
        // loop through the publications and retrieve their corresponding commentaires
        for (const publication of this.publications) {
          this.commentaireService.retrieveCommentaireByPubId(publication.idPub).subscribe(
            commentaires => {
              publication.commentaire = commentaires;
            },
            error => console.error(error)
          );
        }
      },
      error => console.error(error)
    );
  }

  getCommentCount(idPub: number): number {
    let count = 0;
    this.commentaireService.countCommentsInPublication(idPub).subscribe(
      data => {
        count = data;
      },
      error => console.error(error)
    );
    return count;
  }
  
  onDeleteCommentaire(idComm: number): void {
    this.commentaireService.deleteCommentaire(idComm)
      .subscribe(() => {
        this.commentaires = this.commentaires.filter(p => p.idComm !== idComm);

      });
  }


  onDeletePublication(idPub: number): void {
    this.publicationService.deletePublication(idPub)
      .subscribe(() => {
        this.publications = this.publications.filter(p => p.idPub !== idPub);
        this.getPublications();

      });
  }

  /* onUpdatePublication(publication: Publication): void {
     const userId = 1; // replace with the actual user ID
 
     this.publicationService.updatePublication(this.publication.idPub, this.publication.titrePub, this.publication.contenuPub,this.publication.vis, this.publication.dateCreationPub, this.imageFile, this.publication.likeCount, this.publication.dislikeCount, this.publication.isFavorite, this.publication.favoriteDate, userId)
       .subscribe(() => {
         const index = this.publications.findIndex(p => p.idPub === this.publication.idPub);
         if (index !== -1) {
           this.publications[index] = this.selectedPublication;
           //   $('#exampleModalCenter').modal('hide'); // hide the modal after the update is successful
 
 
         }
 
       });
   }*/

  /*onFileSelected(event: any) {
    this.imageFile = event.target.files[0];*/


    toggleLike(idPub: number, likeTypeStr: string) {
      const likeType = likeTypeStr === 'LIKE' ? LikeType.LIKE : LikeType.DISLIKE;
      const idUser=1;
      // Call the toggleLikesP method from the LikeService
      this.likeService.toggleLikesP(idPub, likeType).subscribe(
        (like: Like) => {
          console.log(`Like status updated: ${like.likeType}`);
        },
        (error: any) => {
          console.error(error);
        }
      );
    }
    toggleLikeC(idComm: number, likeTypeStr: string) {
      const likeType = likeTypeStr === 'LIKE' ? LikeType.LIKE : LikeType.DISLIKE;
      const idUser=1;
      // Call the toggleLikesP method from the LikeService
      this.likeService.toggleLikesC(idComm, likeType).subscribe(
        (like: Like) => {
          console.log(`Like status updated: ${like.likeType}`);
        },
        (error: any) => {
          console.error(error);
        }
      );
    }
    
    toggleFavorite( idPub: number) {
      const idUser =1;
      this.publicationService.toggleFavoritePublication( idPub).subscribe(
        (publication: Publication) => {
          console.log('Favorite publication toggled for user:', publication);
        },
        (error: any) => {
          console.error(error);
        }
      );
    }
    
    getFavorites() {
      const idUser =1;

      this.publicationService.getFavoritePublicationsByUserId().subscribe(
        (publications: Publication[]) => {
          console.log('Favorite publications for user:', publications);
        },
        (error: any) => {
          console.error(error);
        }
      );
    }

    getFavoritePublications(): void {
      this.publicationService.getFavoritePublicationsByUserId()
        .subscribe(favoritePublications => this.favoritePublications = favoritePublications);
    }
    
  }



 









