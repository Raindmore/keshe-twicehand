<template>
  <el-card
    shadow="hover"
    class="order-card"
    @click.native="$emit('click', order.oid)"
  >
    <div class="order-card-inner">
      <el-image
        v-if="order.gphoto"
        :src="order.gphoto"
        class="order-card-img"
      >
        <div slot="error">
          <img src="../assets/none.jpg" class="order-card-fallback" />
        </div>
      </el-image>

      <img v-else src="../assets/none.jpg" class="order-card-img" />

      <div class="order-card-info">
        <span class="order-card-name">{{ order.gname }}</span>
        <span class="order-card-oid">订单号：{{ order.oid }}</span>
        <span class="order-card-time">{{ order.otime }}</span>
      </div>

      <div class="order-card-right">
        <span class="order-card-status">
          <el-tag type="success" disable-transitions>交易完成</el-tag>
        </span>
        <div class="order-card-price">
          <span class="order-price-label">成交价：</span>
          <span class="order-price-value">{{ order.oprice }}元</span>
        </div>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: 'OrderCard',
  props: {
    order: {
      type: Object,
      required: true,
      // { oid, gphoto, gname, otime, oprice }
    },
  },
}
</script>

<style scoped>
.order-card {
  cursor: pointer;
  margin-bottom: 30px;
  border-radius: 15px;
}
.order-card-inner {
  display: flex;
}
.order-card-img {
  width: 17%;
  border-radius: 7px;
}
.order-card-fallback {
  width: 100%;
}
.order-card-info {
  padding: 14px;
  width: 60%;
  display: flex;
  flex-direction: column;
}
.order-card-name {
  font-weight: 600;
}
.order-card-oid {
  margin-top: 16px;
}
.order-card-time {
  margin-top: 28px;
}
.order-card-right {
  padding: 14px;
  width: 23%;
}
.order-card-status {
  text-align: end;
  display: block;
}
.order-card-price {
  text-align: right;
  margin-top: 64px;
}
.order-price-label {
  color: #4d4d4d;
  font-size: 17px;
}
.order-price-value {
  color: #ff6700;
  font-size: 18px;
}
</style>
