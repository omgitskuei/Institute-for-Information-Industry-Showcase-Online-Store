// 這邊以上的不用用shoppingCart.在方法上
//**************************
// Shopping Cart function
// automatially run this code when scirpt
var shoppingCart = (function() {
  // Private methods and properties
  var cart = [];
  function Item(name, price, count) {
    this.name = name;
    this.price = price;
    this.count = count;
  }

  // saveCart() -> localStorage
  function saveCart() {
    // 基本localStorage要有name與value：String and numbers最好
    // 所以用JSON obj stringfy()<-裡面放值
    localStorage.setItem("shoppingCart", JSON.stringify(this.cart));
  }

  // loadCart() <- localStorage
  function loadCart() {
    this.cart = JSON.parse(localStorage.getItem("shoppingCart"));
  }

  loadCart();

  // Public methods and properties
  var obj = {};
  // addItemToCart(name, price, count)
  // this.item this就是指shoppingCart該方法的物件
  obj.addItemToCart = function(name, price, count) {
    // 處理unique name用cart[i].name判斷
    for (var i in cart) {
      if (cart[i].name === name) {
        //直接個數加上去
        cart[i].count++;
        saveCart();
        return; // 如果有加到就...結束function
        // 沒有return會發生addItemToCart還是再加一次，因為跳出for迴圈還有下面那段
      }
    }

    console.log("addItemToCart:", name, price, count);

    var item = new Item(name, price, count);
    cart.push(item);
    saveCart();
  };

  // 寫input 的setCount
  obj.setCountForItem = function(name, count) {
    for (var i in cart) {
      if (cart[i].name === name) {
        cart[i].count = count; // !STRING
        break;
      }
      saveCart();
    }
  };

  // removeItemFromCart(name) // 只去掉一個商品
  obj.removeItemFromCart = function(name) {
    for (var i in cart) {
      if (cart[i].name === name) {
        cart[i].count--;
        // 判斷是否數量===0
        if (cart[i].count === 0) {
          // move item from array
          cart.splice(i, 1);
        }
        break;
        // cart[i].count = cart[i].count -1
      }
    }
    saveCart();
  };

  // removeItemFromCartAll(name) // 去掉全部
  obj.removeItemFromCartAll = function(name) {
    for (var i in cart) {
      if (cart[i].name === name) {
        cart.splice(i, 1);
        break;
      }
    }
    saveCart();
  };
  // clearCart()
  obj.clearCart = function() {
    cart = [];
    saveCart();
  };

  // countCart() -> return 總數
  obj.countCart = function() {
    // 每次從0開始算
    var totalCount = 0;
    for (var i in cart) {
      totalCount += cart[i].count;
    }
    return totalCount;
  };
  // totalCart() -> return 總花費
  obj.totalCart = function() {
    var totalCost = 0;
    for (var i in cart) {
      // 這寫法只有一個的總價
      //totalCost += cart[i].price;
      totalCost += cart[i].price * cart[i].count;
    }
    // Convert the number to String
    // 裡面代表小數點第幾位
    return totalCost.toFixed(2);
  };

  // listCart() -> array of Items
  // 在頁面上Display
  obj.listCart = function() {
    //return cart; // 這樣會有淺層複製的問題
    var cartCopy = [];
    for (var i in cart) {
      var item = cart[i];
      //console.log(cart);
      var itemCopy = {};
      for (var p in item) {
        // !!!!這邊看不懂
        itemCopy[p] = item[p];
      }
      itemCopy.total = (item.price * item.count).toFixed(2);
      cartCopy.push(itemCopy);
    }
    return cartCopy;
  };

  return obj;
})();
