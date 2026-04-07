const menuToggle = document.querySelector('.menu-toggle');
const siteHeader = document.querySelector('.site-header');

if (menuToggle && siteHeader) {
  menuToggle.addEventListener('click', () => {
    siteHeader.classList.toggle('menu-open');

    const expanded = menuToggle.getAttribute('aria-expanded') === 'true';
    menuToggle.setAttribute('aria-expanded', String(!expanded));
  });
}

// cerrar menú al hacer click en un enlace
const navLinks = document.querySelectorAll('.main-nav a');

navLinks.forEach(link => {
  link.addEventListener('click', () => {
    siteHeader.classList.remove('menu-open');
    menuToggle.setAttribute('aria-expanded', 'false');
  });
});

