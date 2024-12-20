function decreaseQuantity(element) {
    event.preventDefault();

    let td = element.closest('td');
    let quantitySpan = $(td).find('.quantity');
    let quantity = parseInt(quantitySpan.text(), 10);
    let itemId = $(td).find('#itemId').data('id');

    // 增加数量
    quantity -= 1;

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
            }

        },
        error: function(xhr, status, error) {
            console.error('Error increasing quantity:', error); // 错误处理
        }
    });
}
function increaseQuantity(element) {
    event.preventDefault();

    let td = element.closest('td');
    let quantitySpan = $(td).find('.quantity');
    let quantity = parseInt(quantitySpan.text(), 10);
    let itemId = $(td).find('#itemId').data('id');

    // 增加数量
    quantity += 1;

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

