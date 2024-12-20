// 定义一个缓存对象，用于存储已经加载过的数据
const dataCache = {};

function mouseOver(element) {
    const $floatWindow = $(element).find('.floating-window');
    const productId = $floatWindow.data('product-id');

    // 检查缓存中是否已有该 productId 的数据
    if (dataCache[productId]) {
        // 如果有缓存，直接使用缓存的数据
        populateFloatingWindow($floatWindow, dataCache[productId]);
        $floatWindow.css('opacity', '1'); // 显示浮动窗口
        return;
    }

    // 如果没有缓存，发起 AJAX 请求
    $.ajax({
        url: '/webAPP/product',
        method: 'GET',
        data: {
            product: productId,
            type: 'getItems'
        },
        success: function (response) {
            // 将返回的数据存入缓存
            dataCache[productId] = response;

            // 更新浮动窗口内容
            populateFloatingWindow($floatWindow, response);

            // 显示浮动窗口
            $floatWindow.css('opacity', '1');
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });
}

function mouseOut(element) {
    // 隐藏浮动窗口
    const $floatWindow = $(element).find('.floating-window');
    $floatWindow.css('opacity', '0');
}


function populateFloatingWindow($floatWindow, data) {
    $floatWindow.find('p:not(:first)').remove();

    // 遍历数据，生成新内容并插入
    data.forEach(item => {
        const $newListItem = $(`<p>${item.itemId} ${item.listPrice}</p>`);
        $floatWindow.append($newListItem);
    });
}
