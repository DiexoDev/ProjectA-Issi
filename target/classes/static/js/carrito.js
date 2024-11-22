document.addEventListener("DOMContentLoaded", () => {
    const contadorProductos = document.getElementById('contador-productos');
    const containerCartProducts = document.querySelector('.container-cart-products');
    const addToCartButtons = document.querySelectorAll('.card button');
    const totalPagar = document.querySelector('.total-pagar');
    const resumenPedido = document.getElementById('resumenPedido');

    let contador = 0;
    let total = 0;
    let productosCarrito = [];

    document.querySelector('.container-icon').addEventListener('click', () => {
        containerCartProducts.classList.toggle('hidden-cart');
    });

    addToCartButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            const card = event.target.closest('.card');
            const nombreProducto = card.querySelector('.card-title').textContent;
            const precioProducto = card.querySelector('.card-text.fs-6.precio').textContent.replace('$', '').trim();

            const productoExistente = productosCarrito.find(p => p.nombre === nombreProducto);

            if (productoExistente) {
                productoExistente.cantidad++;
            } else {
                productosCarrito.push({
                    nombre: nombreProducto,
                    precio: parseFloat(precioProducto),
                    cantidad: 1
                });
            }

            contador++;
            total += parseFloat(precioProducto);

            actualizarCarrito();
        });
    });

    function actualizarCarrito() {
        contadorProductos.textContent = contador;
        totalPagar.textContent = `Total: $${total.toFixed(2)}`;

        containerCartProducts.innerHTML = `
            <div class="cart-total">
                <h3>Total:</h3>
                <span class="total-pagar"> $${total.toFixed(2)}</span>
                <button class="btn-pagar" id="btn-pagar">Ir a pagar</button>
            </div>
            ${productosCarrito.map(producto => `
                <div class="cart-product">
                    <div class="info-cart-product">
                        <span>${producto.cantidad} x </span>
                        <p class="titulo-carrito">${producto.nombre}</p>
                        <span class="precio-producto-carrito">$${(producto.precio * producto.cantidad).toFixed(2)}</span>
                    </div>
                </div>
            `).join('')}
        `;

        document.getElementById('btn-pagar').addEventListener('click', mostrarResumen);
    }

    function mostrarResumen() {
        resumenPedido.innerHTML = `
            <h2 id="title-pago">Resumen del Pedido</h2>
            <div class="order-summary">
                ${productosCarrito.map(producto => `
                    <div class="product">
                        <span>${producto.cantidad} x </span>
                        <p class="titulo-carrito">${producto.nombre}</p>
                        <span class="precio-producto-carrito">$${(producto.precio * producto.cantidad).toFixed(2)}</span>
                    </div>
                `).join('')}
                <div class="total">
                    Total: $${total.toFixed(2)}
                </div>
            </div>
            <button id="continue-to-payment">Continuar al Pago</button>

            <div id="payment-section" class="payment-form">
                <h2>Información de Pago</h2>
                <form id="payment-form">
                    <div class="form-group">
                        <label for="card-number">Número de Tarjeta</label>
                        <input type="text" id="card-number" name="card-number" required>
                    </div>
                    <div class="form-group">
                        <label for="card-name">Nombre en la Tarjeta</label>
                        <input type="text" id="card-name" name="card-name" required>
                    </div>
                    <div class="form-group">
                        <label for="expiry-date">Fecha de Expiración</label>
                        <input type="text" id="expiry-date" name="expiry-date" placeholder="MM/AA" required>
                    </div>
                    <div class="form-group">
                        <label for="cvv">CVV</label>
                        <input type="number" id="cvv" name="cvv" required>
                    </div>
                    <button type="submit">Realizar Pago</button>
                </form>
            </div>
            <div id="success-alert" class="alert alert-success" role="alert" style="display: none;">
                ¡Compra realizada exitosamente!
            </div>
        `;

        document.getElementById('continue-to-payment').addEventListener('click', function () {
            document.getElementById('payment-section').style.display = 'block';
            this.style.display = 'none';
        });

        document.getElementById('payment-form').addEventListener('submit', function(e) {
            e.preventDefault();
            document.getElementById('success-alert').style.display = 'block';
            document.querySelector('.order-summary').style.display = 'none';
            document.getElementById('payment-section').style.display = 'none';
            document.getElementById('title-pago').style.display = 'none';
            setTimeout(function() {
                document.getElementById('success-alert').style.display = 'none';
            }, 3000);
        });

        resumenPedido.style.display = 'block';
        resumenPedido.style.position = 'absolute';
        resumenPedido.style.top = '50%';
        resumenPedido.style.left = '50%';
        resumenPedido.style.transform = 'translate(-50%, -50%)';
        resumenPedido.style.marginTop = '5%'
        resumenPedido.style.zIndex = '1000';
    }
});