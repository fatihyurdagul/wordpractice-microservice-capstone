import {Component, OnInit} from '@angular/core';
import {ApiService} from 'src/app/api/api.service';

import {Validators, FormBuilder, FormGroup, FormControl} from '@angular/forms';
import {FeedProviderService} from '../services/feed.provider.service';

import {LoadingController, ModalController} from '@ionic/angular';


@Component({
    selector: 'app-feed-upload',
    templateUrl: './feed-upload.component.html',
    styleUrls: ['./feed-upload.component.scss'],
})
export class FeedUploadComponent implements OnInit {
    uploadForm: FormGroup;

    constructor(private feed: FeedProviderService,
                private formBuilder: FormBuilder,
                private loadingController: LoadingController,
                private modalController: ModalController) {
    }

    ngOnInit() {
        this.uploadForm = this.formBuilder.group({
                name: new FormControl('', Validators.required),
                content: new FormControl('', Validators.required)
            }
        );
    }

    onSubmit($event) {
        $event.preventDefault();
        this.loadingController.create();

        const name = this.uploadForm.controls.name.value;
        const content = this.uploadForm.controls.content.value;

        this.feed.createDictionary('fatih@gmail.com', name, content)
            .then((result) => {
                this.modalController.dismiss();
                this.loadingController.dismiss();
            });
    }

    cancel() {
        this.modalController.dismiss();
    }
}
