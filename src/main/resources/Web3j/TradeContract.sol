pragma solidity ^0.8.0;

contract TradeContract {
    struct Trade {
        string OrderID;
        string buyer;
        string seller;
        uint256 totalPrice;
        uint256 orderTime;
        uint256 payTime;
    }

    Trade[] trades;

    function addTrade(string memory OrderID, string memory buyer, string memory seller, uint256 totalPrice, uint256 orderTime, uint256 payTime) public {
        trades.push(Trade(OrderID, buyer, seller, totalPrice, orderTime, payTime));
    }

    function getTrades() public view returns (Trade[] memory) {
        return trades;
    }
}