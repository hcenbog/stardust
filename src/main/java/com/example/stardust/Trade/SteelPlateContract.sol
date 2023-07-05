// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;contract SteelPlateContract {
    // 钢板结构体
    struct SteelPlate {
        uint id; // 钢板编号
        string name; // 钢板名称
        uint price; // 钢板价格
        uint quantity; // 钢板数量
        address seller; // 卖家地址
        address buyer; // 买家地址
    }    // 钢板映射，将钢板编号映射到钢板结构体
    mapping(uint => SteelPlate) public steelPlates;    
    // 钢板数量
    uint public steelPlateCount;   
     // 添加钢板事件
    event SteelPlateAdded(uint id, string name, uint price, uint quantity, address seller);   
     // 购买钢板事件
    event SteelPlateBought(uint id, address buyer);
    // 添加钢板
    function addSteelPlate(string memory name, uint price, uint quantity) public {
        // 钢板数量加1
        steelPlateCount++;       
         // 创建钢板结构体并添加到映射中
        steelPlates[steelPlateCount] = SteelPlate(steelPlateCount, name, price, quantity, msg.sender, address(0));        // 触发添加钢板事件
        emit SteelPlateAdded(steelPlateCount, name, price, quantity, msg.sender);
    }    
    // 购买钢板
    function buySteelPlate(uint id) public payable {
        // 获取钢板结构体
        SteelPlate storage steelPlate = steelPlates[id];        
        // 检查钢板是否已售出
        require(steelPlate.buyer == address(0), "Steel plate already sold");        
        // 检查支付的以太币数量是否正确
        require(msg.value == steelPlate.price, "Incorrect amount of ether sent");        
        // 更新钢板结构体中的买家地址和数量
        steelPlate.buyer = msg.sender;
        steelPlate.quantity--;        
        // 将以太币转给卖家
        payable(steelPlate.seller).transfer(msg.value);        
        // 触发购买钢板事件
        emit SteelPlateBought(id, msg.sender);
    }
}