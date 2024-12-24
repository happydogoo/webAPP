function decreaseQuantity(element) {
    event.preventDefault();

    let td = element.closest('td');
    let quantitySpan = $(td).find('.quantity');
    let quantity = parseInt(quantitySpan.text(), 10);
    if(quantity>1){
    let itemId = $(td).find('#itemId').data('id');
    let totalPriceTd=$("#totalPrice1");
    let totalPriceText = $("#totalPrice1").text();
    let totalPrice = totalPriceText.replace("总价:", "").trim();
    totalPrice = parseFloat(totalPrice);
    let priceTd=$(element).closest('tr').find('td:nth-child(3)');
    let price=parseFloat(priceTd.text().trim());
    quantity -= 1;
    totalPrice-=price;
    $.ajax({
        url: '/webAPP/cart',
        method: 'POST',
        data: {
            quantity: quantity,
            type: 'updateQuantity', // 类型为增加数量
            itemId: itemId
        },
        dataType:'json',
        success: function(response) {
            if(response.status==='success') {
                quantitySpan.text(response.currentQuantity); // 更新数量显示
                totalPriceTd.text('总价: '+totalPrice.toFixed(2));
            }

        },
        error: function(xhr, status, error) {
            console.error('Error increasing quantity:', error); // 错误处理
        }
    });
    }
}
function increaseQuantity(element) {
    event.preventDefault();

    let td = element.closest('td');
    let quantitySpan = $(td).find('.quantity');
    let quantity = parseInt(quantitySpan.text(), 10);

    let itemId = $(td).find('#itemId').data('id');
    let totalPriceTd = $("#totalPrice1");
    let totalPriceText = $("#totalPrice1").text();
    let totalPrice = totalPriceText.replace("总价:", "").trim();
    totalPrice = parseFloat(totalPrice);
    let priceTd = $(element).closest('tr').find('td:nth-child(3)');
    let price = parseFloat(priceTd.text().trim());

    // 增加数量
    quantity += 1;
    totalPrice += price;

    $.ajax({
        url: '/webAPP/cart',
        method: 'POST',
        data: {
            quantity: quantity,
            type: 'updateQuantity', // 类型为增加数量
            itemId: itemId
        },
        dataType: 'json',
        success: function(response) {
            if (response.status === 'success') {
                quantitySpan.text(response.currentQuantity); // 更新数量显示
                totalPriceTd.text('总价: ' + totalPrice.toFixed(2)); // 更新总价显示
            }
        },
        error: function(xhr, status, error) {
            console.error('Error increasing quantity:', error); // 错误处理
        }
    });
}

function deleteItem(element) {
    event.preventDefault();
    let form = element.closest("form");
    let itemId = form.querySelector('input[name="itemId"]').value;

    $.ajax({
        url: '/webAPP/cart', // 替换为你的实际路径
        method: 'POST',
        data: {
            type: 'removeFromCart',
            itemId: itemId
        },
        success: function(response) {
            if(response.status==='success') {
                form.closest("tr").remove();
            }
            },
        error: function(xhr, status, error) {
            console.error("Error removing item:", error);
        }
    });
}
function addCheckoutInfo(element){
    event.preventDefault();



    const popup = document.getElementById('popup');
    const overlay = document.getElementById('overlay');
    popup.style.display = 'block'; // 显示弹窗
    overlay.style.display = 'block'; // 显示遮罩层


}

function closePopup(element){
    event.preventDefault();
    event.stopPropagation();
    const shipAddr1 = document.getElementById("shipAddr1").value;
    const shipZip = document.getElementById("shipZip").value;
    const shipCountry = document.getElementById("shipCountrySelect").value;
    const courier = document.getElementById("courier").value;
    const totalPrice = document.getElementById("totalPrice").value;
    const name = document.getElementById("name").value;
    const creditCard = document.getElementById("creditCard").value;
    const cardType = document.getElementById("cardType").value;
    const countryMapping = {
        "US": "美国",
        "CA": "加拿大",
        "GB": "英国",
        "DE": "德国",
        "FR": "法国",
        "IT": "意大利",
        "ES": "西班牙",
        "CN": "中国",
        "IN": "印度",
        "JP": "日本"
    };
    let fullShipCountry=countryMapping[shipCountry];
    $.ajax({
        url: '/webAPP/cart',
        method: 'POST',
        data: {
            type: 'saveCheckoutInfo',
            shipAddr1: shipAddr1,
            shipZip: shipZip,
            shipCountry: shipCountry,
            courier: courier,
            totalPrice: totalPrice,
            name: name,
            creditCard: creditCard,
            cardType: cardType

        },
        success: function(response) {
            if(response.status==='success') {

                let addCheckoutInfoLi=$('#addCheckoutInfo');

                $('#addCheckoutInfo').before(`
    <li>
        <a href="#" 
           onclick="chooseCheckoutInfo(this)" 
           data-ship-addr1="${shipAddr1}" 
           data-ship-zip="${shipZip}" 
           data-ship-country="${shipCountry}" 
           data-courier="${courier}" 
           data-name="${name}" 
           data-credit-card="${creditCard}" 
           data-card-type="${cardType}">
            
        <h2 style="font-weight: bold; margin-bottom: 5px;">${fullShipCountry}                      ${name}</h2>
        <hr style="border: 1px solid #808080; margin: 10px 0;width: 100%">
        <h3 style="margin-bottom: 5px;">
            ${shipAddr1}
        </h3>
        <h3>邮编: ${shipZip}</h3>
        <h3  style="border-top: 2px solid #ddd;"  ></h3>
        <h3>快递公司: ${courier}</h3>
        <h3>${cardType}: ${creditCard}</h3>
        </a>
    </li>
`);
                document.getElementById("popup").style.display = "none";
                document.getElementById("overlay").style.display="none";
            }
        },
        error: function(xhr, status, error) {
            console.error("Error saveCheckoutInfo:", error);
        }
    });
}
function chooseCheckoutInfo(anchor) {
    event.preventDefault();
    const allLiElements = document.querySelectorAll('li a');
    allLiElements.forEach(liAnchor => {
        liAnchor.style.boxShadow = ''; // Reset the boxShadow
    });
    anchor.style.boxShadow = "0 0 20px gold";
    //box-shadow 0 0 20px gold
    const shipAddr1Input = document.getElementById("shipAddr1");
    const shipZipInput = document.getElementById("shipZip");
    const shipCountrySelect = document.getElementById("shipCountrySelect");
    const courierInput = document.getElementById("courier");
    const nameInput = document.getElementById("name");
    const creditCardInput = document.getElementById("creditCard");
    const cardTypeInput = document.getElementById("cardType");

    // 国家中英文对照表
    const countryMap = {
        "美国": "US",
        "加拿大": "CA",
        "英国": "GB",
        "德国": "DE",
        "法国": "FR",
        "意大利": "IT",
        "西班牙": "ES",
        "中国": "CN",
        "印度": "IN",
        "日本": "JP"
    };

    // 从 <a> 标签中获取 data-* 属性值
    const shipAddr1 = anchor.getAttribute("data-ship-addr1");
    const shipZip = anchor.getAttribute("data-ship-zip");
    const shipCountry = anchor.getAttribute("data-ship-country"); // 获取中文国家名
    const courier = anchor.getAttribute("data-courier");
    const name = anchor.getAttribute("data-name");
    const creditCard = anchor.getAttribute("data-credit-card");
    const cardType = anchor.getAttribute("data-card-type");

    // 设置表单值
    shipAddr1Input.value = shipAddr1;
    shipZipInput.value = shipZip;
    shipCountrySelect.value = countryMap[shipCountry] || ""; // 根据中文全称获取英文缩写
    courierInput.value = courier;
    nameInput.value = name;
    creditCardInput.value = creditCard;
    cardTypeInput.value = cardType;
}
function isCartEmpty(element){

        const itemInput = document.querySelector('input[type="hidden"][name="itemId"]');

        if (!itemInput) {
            event.preventDefault(); // 阻止默认行为
            event.stopPropagation(); // 阻止冒泡
            return false; // 防止后续代码执行
        }

}
