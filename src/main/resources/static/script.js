document.addEventListener('DOMContentLoaded', () => {
    const basketItemsContainer = document.getElementById('basket-items');
    const totalPriceElement = document.getElementById('total-price');
    const checkoutButton = document.getElementById('checkout-button');

    function populateBasket(basketItems) {
        let totalPrice = 0;
        basketItemsContainer.innerHTML = '';

        basketItems.forEach(item => {
            const row = document.createElement('tr');
            const total = item.price * item.quantity;
            totalPrice += total;

            row.innerHTML = `
                <td>${item.productName}</td>
                <td>${item.description}</td>
                <td>$${item.price.toFixed(2)}</td>
                <td>${item.quantity}</td>
                <td>$${total.toFixed(2)}</td>
                <td><button class="remove-button" data-product-id="${item.productId}">Remove</button></td>
            `;
            basketItemsContainer.appendChild(row);
        });

        totalPriceElement.innerText = `$${totalPrice.toFixed(2)}`;
    }

    fetch('/basket')
        .then(response => response.json())
        .then(data => {
            populateBasket(data.items);
        })
        .catch(error => {
            console.error('Error fetching basket items:', error);
        });

    checkoutButton.addEventListener('click', () => {
        alert('Proceeding to checkout!');
    });

    basketItemsContainer.addEventListener('click', (event) => {
        if (event.target.classList.contains('remove-button')) {
            const productId = event.target.getAttribute('data-product-id');
            fetch(`/basket/remove/${productId}`, { method: 'GET' })
                .then(() => {
                    fetch('/basket')
                        .then(response => response.json())
                        .then(data => {
                            populateBasket(data.items);
                        });
                })
                .catch(error => {
                    console.error('Error removing item from basket:', error);
                });
        }
    });
});
