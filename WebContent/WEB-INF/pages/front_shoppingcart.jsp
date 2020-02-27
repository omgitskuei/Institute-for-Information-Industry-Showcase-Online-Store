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
<title>購物車</title>
 </head>
  <body>
    <!-- START -->
  <%@include file="/WEB-INF/pages/front_navbar.jsp" %>

    <!-- PAGE HEADER -->

    <header id="page-header">
      <div class="dark-overlay-title">
        <div class="container">
            <div class="row">
                <div class="col-md-6 m-auto text-center">
                    <h1>購物車</h1>
                    <p>您所購買的商品</p>
                </div>
            </div>
        </div>
      </div>
    </header>

<!-- SHOPPINGCART SECTION -->
<section class="py-3">
<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                     <tr>
                            <th scope="col"><label class="img"></label> </th>
                            <th scope="col"><label class="name">商品</label></th>
                      		<th scope="col" class="text-left"><label class="quentity">數量</label></th>
                            <th scope="col" class="text-right"><label class="price">單價</label></th>
                            <th scope="col" class="text-right"><label class="price">小記</label></th>
                            <th scope="col" class="text-left"><label class="price">增加</label></th>
                            <th scope="col" class="text-left"><label class="price">減少</label></th>
                            <th scope="col" class="delete"><label class="price">刪除</label></th>
                            <th><label class="delete"></label></th>
                        </tr>
                    </thead>
                    <tbody class="list_area" id="show-cart">
   <!--                      <tr>
                            <td><img src="https://source.unsplash.com/random/51x50/?food" /> </td>
                            <td>商品一</td>
                            <td>足夠</td>
                            <td><input class="form-control" type="text" value="2" /></td>
                            <td class="text-right price">NT$ 100</td>
                            <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                        </tr> -->
                       
                     <!--    <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>運費</td>
                            <td class="text-right">NT$ 18</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><strong>總金額</strong></td>
                            <td class="text-right"><strong id="total-cart">198</strong></td>
                        </tr> -->
                    </tbody>
                    	<tbody class="list_area">
                     <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>購買總金額</td>
                            <td class="text-right" id="total-cart">NT$ 180</td>
                      </tr>
                   </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row d-flex justify-content-end">
                <div class="col-sm-2">
                    <a href=<jstl:url value="/directservices"/> class="btn btn-lg btn-block btn-light">繼續購物</a>
                </div>
                <div class="col-sm-2">
                    <button class="btn btn-lg btn-block btn-danger text-white" id="clear-cart">清除商品</button>
                </div>
                <div class="col-sm-2">
                    <a href="checkout.html" onclick="" class="btn btn-lg btn-block btn-success text-white">結帳</a>
                </div>
            </div>
        </div>
    </div>
</div>
</section>


    <!-- FOOTER -->
    <footer id="main-footer" class="text-center p-4 bg-dark text-white">
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
    <script src="https://kit.fontawesome.com/c3dc04dc4d.js" crossorigin="anonymous"></script>
    <script>
      // Get the current year for copyright
      $("#year").text(new Date().getFullYear());
      

      
    </script>
    
         <script>
    // ***************************************************
    // Shopping Cart functions

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

          console.log("Click add to cart:"+name+" "+price);

          shoppingCart.addItemToCart(id, img, name, price, 1);
          displayCart();
        });

       // 寫清除
        $("#clear-cart").click(function(event){
          shoppingCart.clearCart();
          displayCart();
        });



        //displayCart();
        function displayCart() {
          console.log("*** Display Cart ***");
          var cartArray = shoppingCart.listCart();
          console.log("*** Count Cart:" + cartArray.length);
          var output = "";
          for(var i in cartArray) {
            output += "<tr><td>"+ cartArray[i].name + "</td><td>" + "<img src='" + cartArray[i].img + "' width='50px' />" + "</td><td>"  +"<input class='item-count form-control' type='text' data-name='"+cartArray[i].name+"' value='"+cartArray[i].count+"'/>" + "</td>"+"<td class='text-right price'>" + cartArray[i].price + "</td>" + "<td class='text-right price'>"+cartArray[i].total+ 
            "</td>"+"<td><button class='plus-item' data-name='"+cartArray[i].name+"'>+</button></td>"+
            "<td><button class='subtract-item' data-name='"+cartArray[i].name+"'>-</button></td>" + "<td><button class='delete-item' data-name='"+cartArray[i].name+"'>X</button></td></tr>"
            
          }	
          // html會渲染所有東西
          $("#show-cart").html(output);
          $("#count-cart").html(shoppingCart.countCart());
          $("#total-cart").html(shoppingCart.totalCart());
        }
        // 不太確定這邊的on是為什麼
        $("#show-cart").on("click", ".delete-item", function(event){
          // this X button我們按的 
          var name = $(this).attr("data-name");
          shoppingCart.removeItemFromCartAll(name);
          displayCart();
        });
        // 寫加法的
        $("#show-cart").on("click", ".plus-item", function(event){
          var name = $(this).attr("data-name");
          // 對應下面的addItemToCart function
          // 不用放任何事情在price，但如果刪掉可能會undefined
          shoppingCart.addItemToCart(name, 0, 1);
          displayCart();

        });
        // 寫減法的
        $("#show-cart").on("click", ".subtract-item", function(){
          var name = $(this).attr("data-name");
          shoppingCart.removeItemFromCart(name);
          displayCart();
        });
        // 寫輸入數字的，注意是change
        $("#show-cart").on("change", ".item-count", function(event){
          var name = $(this).attr("data-name");
          // 要取得數字，取得數值是val
          // 避免String + Number = String現象發生所以加Number
          var count = Number($(this).val());
          shoppingCart.setCountForItem(name, count);
          displayCart();
        });


       

        displayCart();   
        
        
        // ajax 送出購物車功能
      
        
    			
    </script>
    <script>
    function addToOrder(productId,productCount) {
    	var buyData = {};
		buyData.userID = "${userID}";
		buyData.productID = productID;
		buyData.productCount = productCount;
		$.ajax({
			async : false,
			type : 'POST',
			url : '', // post 的路徑
			data : buyData,
			dataType : 'json',
			success : console.log("adding order success.")
		});
    }
    </script>
    </body>
</html>
