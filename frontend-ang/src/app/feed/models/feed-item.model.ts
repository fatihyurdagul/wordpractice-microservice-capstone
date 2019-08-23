export interface FeedItem {
    dictionaryId: number;
    username: string;
    content: string;
    name: string;
}

export const feedItemMocks: FeedItem[] = [
    {
        dictionaryId: 0,
        username: '/assets/mock/xander0.jpg',
        content: 'Such a cute pup',
        name: 'Such a cute pup'
    },
    {
        dictionaryId: 1,
        username: '/assets/mock/xander1.jpg',
        content: 'Who\'s a good boy?',
        name: 'Name'
    },
    {
        dictionaryId: 2,
        username: '/assets/mock/xander2.jpg',
        content: 'Majestic.',
        name: 'name'
    }
];
