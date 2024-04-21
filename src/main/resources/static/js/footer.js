

class BlogHeader extends HTMLElement {
    constructor() {
        super();


    }
}

window.customElements.define(
    'blog-header',
    BlogHeader,
    {extends: 'p'},
)