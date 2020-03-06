<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="" method="post" action="Ecpay.php"><label>編號 (MerchantTradeNo):
        <input type="text" name="MerchantTradeNo" value="商店的訂單編號" class="form-control">
        不可重複使用。英數字大小寫混合
    </label>
    <label class="col-xs-12">時間 (MerchantTradeDate):
        <input type="text" name="MerchantTradeDate" value="當下時間" class="form-control">
        yyyy/MM/dd HH:mm:ss
    </label>
    <label class="col-xs-12">類型 (PaymentType):
        <input type="text" name="PaymentType" value="aio" class="form-control">
        aio
    </label>
    <label class="col-xs-12">金額 (TotalAmount):
        <input type="text" name="TotalAmount" value="本次要負的金額" class="form-control">
        請帶整數，不可有小數點 僅限新台幣 金額不可為 0 元，如果是 CVS 或 BARCODE 最低限制為 30 元，最高限制為 30,000 元
    </label>
    <label class="col-xs-12">描述 (TradeDesc):
        <input type="text" name="TradeDesc" value="Desc" class="form-control">
    </label>
    <label class="col-xs-12">名稱 (ItemName):
        <input type="text" name="ItemName" value="A#B" class="form-control">
        如果商品名稱有多筆，需在金流選擇頁一行一行顯示商品名稱的話，商品名稱請以符號#分隔。
    </label>
    <label class="col-xs-12">回傳網址 (ReturnURL):
        <input type="text" name="ReturnURL" value="要接收綠界回傳付款資訊的網址" class="form-control">
    </label>
    <label class="col-xs-12">付款方式 (ChoosePayment):
        <input type="text" name="ChoosePayment" value="ALL">
        綠界提供下列付款方式，請於建立訂單時傳送過來:Credit:信用卡及銀聯卡
UnionPay:銀聯卡(需申請開通)
WebATM:網路ATM
ATM:自動櫃員機
CVS:超商代碼
BARCODE:超商條碼
ALL:不指定付款方式，由綠界顯示付款方式選擇頁面。
    </label>
 
    <button type="submit" class="btn btn-default">付款</button>
</form>
</body>
</html>