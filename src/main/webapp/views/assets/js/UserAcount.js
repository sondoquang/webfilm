/*========== SHOW MENU =========*/
const navMenu = document.getElementById('nav-menu'),
    navToggle = document.getElementById('nav-toggle'),
    navClose  = document.getElementById('nav-close');

/*============ Menu show ============*/
navToggle.addEventListener('click',()=>{
    navMenu.classList.add('show-menu');
});

navClose.addEventListener('click',()=>{
   navMenu.classList.remove('show-menu');
});


/*=========== SEARCH ===========*/
const search = document.getElementById("search"),
    searchBtn = document.getElementById('search-btn'),
    searchClose = document.getElementById('search-close');

/*Search show*/
searchBtn.addEventListener('click',()=>{
    search.classList.add('show-search');
});

/* Search close */
searchClose.addEventListener('click',()=>{
    search.classList.remove('show-search');
});