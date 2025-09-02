function getUserFromDB(userId) {
  return new Promise((resolve) => {
    setTimeout(() => {
      console.log("✅ Lấy user từ DB");
      resolve({ id: userId, name: "Thành" });
    }, 1000);
  });
}

function getOrdersByUserId(userId) {
  return new Promise((resolve) => {
    setTimeout(() => {
      console.log("✅ Lấy danh sách đơn hàng cho userId:", userId);
      resolve([
        { orderId: 101, item: "Áo thể thao" },
        { orderId: 102, item: "Giày bóng rổ" },
      ]);
    }, 1000);
  });
}

getUserFromDB(1)
  .then((user) => {
    console.log("User:", user);
    return getOrdersByUserId(user.id);
  })
  .then((orders) => {
    console.log("Orders:", orders);
  })
  .catch((err) => {
    console.error("Lỗi:", err);
  });
