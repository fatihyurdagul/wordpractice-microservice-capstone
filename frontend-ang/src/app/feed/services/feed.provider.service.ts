import {Injectable} from '@angular/core';
import {FeedItem, feedItemMocks} from '../models/feed-item.model';
import {BehaviorSubject} from 'rxjs';

import {ApiService} from '../../api/api.service';

@Injectable({
    providedIn: 'root'
})
export class FeedProviderService {
    currentFeed$: BehaviorSubject<FeedItem[]> = new BehaviorSubject<FeedItem[]>([]);

    constructor(private api: ApiService) {
    }

    async getFeed(username: string): Promise<BehaviorSubject<FeedItem[]>> {
        const req = await this.api.get('/dictionary/' + username);
        const items = <FeedItem[]> req.data;
        this.currentFeed$.next(items);
        return Promise.resolve(this.currentFeed$);
    }

    async uploadFeedItem(caption: string, file: File): Promise<any> {
        const res = await this.api.upload('/dictionary', file, {caption: caption, url: file.name});
        const feed = [res, ...this.currentFeed$.value];
        this.currentFeed$.next(feed);
        return res;
    }

    async createDictionary(username: string, name: string, content: string): Promise<any> {

        return this.api.post('/dictionary/add',
            {name: name, content: content, username: username}).then((res) => {
            const data = res.data;
            return res;
        }).catch((e) => {
            throw e;
        });

        // const feed = [res, ...this.currentFeed$.value];
        // this.currentFeed$.next(feed);
        // return res;
    }

}

// async getFeed() {
//   const url = `${API_HOST}/feed`;

//   const req = this.http.get(url, this.httpOptions).pipe(
//     map(this.extractData));
//     // catchError(this.handleError));
//   const resp = <any> (await req.toPromise());
//   return resp.rows;
// }
