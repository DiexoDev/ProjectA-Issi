document.getElementById('admin-login').addEventListener('click', function(event) {
    event.preventDefault();
    var code = prompt('Ingrese el código de administrador:');
    if (code === 'admin') {
        window.location.href = '/admin';
    } else {
        alert('Código incorrecto');
    }
});