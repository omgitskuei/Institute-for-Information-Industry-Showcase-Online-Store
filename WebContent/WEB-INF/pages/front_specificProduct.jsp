<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Noto+Sans+TC&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.css" integrity="sha256-HAaDW5o2+LelybUhfuk0Zh2Vdk8Y2W2UeKmbaXhalfA=" crossorigin="anonymous" />
    <style>
    	<%@include file="/WEB-INF/css/bootstrap.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    <title>單一商品</title>
  </head>

  <body>
    <!-- START -->
<%@include file="/WEB-INF/pages/front_navbar.jsp" %>

    <!-- PAGE HEADER -->
<section>
    <header id="page-header">
      <div class="dark-overlay-title">
        <div class="container">
            <div class="row">
                <div class="col-md-6 m-auto text-center">
                    <h1>商品</h1>
          
                </div>
            </div>
          </div>
        </div>
    </header>
  </section>

<section class="my-3">
    <div class="container">
      <div class="row">
      <div class="col-md-12">
        <ol class="breadcrumb">
          <li class="breadcrumb-item">商品全覽</li>
        </ol>
      </div>
    </div>
  </div>
  </section>

  <!-- STOCK -->
  <section class="py-3">
  <div class="container">
      <div class="row">
          <div class="col-md-4">
              <div class="sticky-top" style="top: 10px;">
                  <img src="${theProduct.productImg}" class="mb-3 img-fluid" alt="">
                  <h1>${theProduct.productName}</h1>
                  <p>商品 ID: ${theProduct.productID}</p>
                  <div class="d-flex justify-content-end align-items-end">
                      <del class="text-muted">售價 ${theProduct.productPrice+10.00} 新台幣</del>
                      <div class="h3 ml-auto mb-0 text-danger">
                          <small>特價 $NT</small>
                          <strong> ${theProduct.productPrice}</strong>
                      </div>
                  </div>
                  <hr>
                  <div class="input-group mt-3">
                      <a href="#" class="btn btn-success text-white  mt-2 ml-2 add-to-cart" 
                      data-id="${theProduct.productID}" 
                      data-img="${theProduct.productImg}" 
                      data-name="${theProduct.productName}" 
                      data-price="${theProduct.productPrice}"
                      data-toggle="modal" 
                      data-target="#modalCartConfirm"
                      >加入購物車</a>                  
                       </div>
              </div>

          </div>
          <div class="col-md-8">
              <h2> ${theProduct.productName} 特色</h2>
              <p> ${theProduct.productDescription} </p>

              <div class="card mt-5">
                  <div class="card-body bg-light">
                      <h2 class="text-center">購物說明</h2>
                      <p>
                        <ul>
                          <li>
                            選好數量（斤），按下加入購物車
                          </li>
                          <li>
                            有效退費或換貨，只要尚未經過任何加工
                          </li>
                          <li>
                            選好商品到資料庫做結帳
                          </li>
                          <li>
                            有任何問題請透過聯絡我們
                          </li>
                        </ul>
                      </p>
                  </div> 
              </div>
          </div>
      </div>
  </div>
</section>

<!-- Model -->
<!--Modal: modalConfirmDelete-->
<div class="modal fade" id="modalCartConfirm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog modal-sm modal-notify modal-info" role="document">
    <!--Content-->
    <div class="modal-content text-center">
      <!--Header-->
      <div class="modal-header d-flex justify-content-center">
        <h5>已加入購物車!!</h5>
      </div>

      <!--Body-->
      <div class="modal-body">

        <i class="fas fa-shopping-cart fa-4x"></i>

      </div>

      <!--Footer-->
      <div class="modal-footer justify-content-center">
        <a type="button" class="btn btn-success text-white mt-2 ml-2" data-dismiss="modal">OK !</a>
      </div>
    </div>
    <!--/.Content-->
  </div>
</div>
<!--Modal: modalConfirmDelete-->
<!-- Model -->


    <!-- FOOTER -->
    <footer id="main-footer" class="text-center p-4 text-white bg-dark">
      <div class="container">
        <div class="row">
          <div class="col">
            <p>版權所有 &copy;
              <span id="year"> FarmVille</span>
            </p>
          </div>
        </div>
      </div>
    </footer>



    <script
      src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
      integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
      integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
      crossorigin="anonymous"
    ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js" integrity="sha256-NXRS8qVcmZ3dOv3LziwznUHPegFhPZ1F/4inU7uC8h0=" crossorigin="anonymous"></script>
    <script>
      // Get the current year for copyright
      $("#year").text(new Date().getFullYear());

    </script>
    <script type="text/javascript">
     var shoppingCart = (function () {
        // Private methods and properties
        var cart = [];

        function Item(id, img, name, price, count) {
        	this.id = id
        	this.img = img
            this.name = name
            this.price = price
            this.count = count
        }

        function saveCart() {
            localStorage.setItem("shoppingCart", JSON.stringify(cart));
        }

        function loadCart() {
            cart = JSON.parse(localStorage.getItem("shoppingCart"));
            if (cart === null) {
                cart = []
            }
        }

        loadCart();



        // Public methods and properties
        var obj = {};

        obj.addItemToCart = function (id, img, name, price, count) {
            for (var i in cart) {
                if (cart[i].name === name) {
                    cart[i].count += count;
                    saveCart();
                    return;
                }
            }

            console.log("addItemToCart:", name, price, count);

            var item = new Item(id, img, name, price, count);
            cart.push(item);
            saveCart();
        };

        obj.setCountForItem = function (name, count) {
            for (var i in cart) {
                if (cart[i].name === name) {
                    cart[i].count = count;
                    break;
                }
            }
            saveCart();
        };


        obj.removeItemFromCart = function (name) { // Removes one item
            for (var i in cart) {
                if (cart[i].name === name) { // "3" === 3 false
                    cart[i].count--; // cart[i].count --
                    if (cart[i].count === 0) {
                        cart.splice(i, 1);
                    }
                    break;
                }
            }
            saveCart();
        };


        obj.removeItemFromCartAll = function (name) { // removes all item name
            for (var i in cart) {
                if (cart[i].name === name) {
                    cart.splice(i, 1);
                    break;
                }
            }
            saveCart();
        };


        obj.clearCart = function () {
            cart = [];
            saveCart();
        }


        obj.countCart = function () { // -> return total count
            var totalCount = 0;
            for (var i in cart) {
                totalCount += cart[i].count;
            }

            return totalCount;
        };

        obj.totalCart = function () { // -> return total cost
            var totalCost = 0;
            for (var i in cart) {
                totalCost += cart[i].price * cart[i].count;
            }
            return totalCost.toFixed(2);
        };

        obj.listCart = function () { // -> array of Items
            var cartCopy = [];
            console.log("Listing cart");
            console.log(cart);
            for (var i in cart) {
                console.log(i);
                var item = cart[i];
                var itemCopy = {};
                for (var p in item) {
                    itemCopy[p] = item[p];
                }
                itemCopy.total = (item.price * item.count).toFixed(2);
                cartCopy.push(itemCopy);
            }
            return cartCopy;
        };

        // ----------------------------
        return obj;
    })();
    </script>

       
       <script>
        // event就像一個function可以帶值進去
        $(".add-to-cart").click(function(event){
          // 不要a連結做預設動作
          event.preventDefault();
          // this代表點下去的那個
          // .attr("可以加一個屬性")
          var name = $(this).attr("data-name");
          // Number() convert to the Number
          var price = Number($(this).attr("data-price"));
          
          var img = $(this).attr("data-img");
          var id = Number($(this).attr("data-id"));

          console.log("Click add to cart:"+name+" "+price);

          shoppingCart.addItemToCart(id, img, name, price, 1);
          displayCart();
        });

       // 寫清除
        $("#clear-cart").click(function(event){
          shoppingCart.clearCart();
          displayCart();
        });
       </script>
  </body>
</html>
