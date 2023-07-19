/*
    app.js

*/
 window.addEventListener('DOMContentLoaded', () => {
     const search = document.getElementById('searchBox');

     const allProducts = productService.getProducts();
     productDiv(allProducts);

     searchBox.addEventListener('keyup', () => {
         const searchTerm = search.value;

         if (searchTerm === ''){
             const allProducts = productService.getProducts();
             productDiv(allProducts);
         }else{
             const filteredProducts = productService.searchProducts(searchTerm);
             productDiv(filteredProducts);
         }
     });
 });



const allProducts = productService.getProducts();

document.addEventListener('DOMContentLoaded', () => {
    
      
        for (product of allProducts){
            let div = productToDiv(product);
            document.getElementById('product-cards').appendChild(div);
        }
        });

        function productToDiv(product) {
            let productDiv = document.createElement('div');
            productDiv.classList.add('product-card');
           
       
       
            let sku = document.createElement('div');
            sku.innerText = product.productSku;
            sku.classList.add('sku');
            productDiv.appendChild(sku);

            
            let price = document.createElement('div');
            price.innerText = product.price;
            price.classList.add('price');
            productDiv.appendChild(price);

            let name = document.createElement('div');
            name.setAttribute('data-id', product.productId)
            name.innerText = product.name;
            name.classList.add('product-name');
            
            name.addEventListener ('click', () => alert (product.description));

             productDiv.appendChild(name);

            

            let image = document.createElement('div');
            image.classList.add('product-image');

            let productImage = document.createElement('img');
            productImage.src = product.imageName;
            image.appendChild(productImage);
            productDiv.appendChild(image);

           
            let cart = document.createElement ('div');
            cart.classList.add('cart')
            
            let icon = document.createElement('i');
            icon.classList.add('fa-solid', 'fa-cart-plus', 'icon', 'action');
            
            icon.addEventListener('click', () => {
                window.alert (product.name + 'has been added to cart')
            })
            cart.appendChild(icon);
            productDiv.appendChild(cart);

            

            

return productDiv;}
