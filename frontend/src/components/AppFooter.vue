<template>
  <footer class="app-footer">
    <div class="app-footer-inner">
      <div class="footer-brand">
        <img src="../assets/logo.png" alt="二手集市" class="footer-logo" />
        <span class="footer-name">二手集市</span>
      </div>
      <div class="footer-copy">
        © {{ currentYear }} 二手集市 · 校园二手物品交易平台
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'AppFooter',
  data() {
    return {
      currentYear: new Date().getFullYear(),
    }
  },
}
</script>

<style scoped>
.app-footer {
  background: #f5f5f5;
  padding: 32px 0;
  margin-top: 60px;
  border-top: 1px solid var(--color-divider);
}
.app-footer-inner {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 16px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 12px;
}
.footer-brand {
  display: flex;
  align-items: center;
  gap: 8px;
}
.footer-logo {
  width: 22px;
  height: 22px;
}
.footer-name {
  font-size: 16px;
  font-weight: 600;
  color: var(--color-primary);
}
.footer-copy {
  font-size: 13px;
  color: #999;
}

@media (max-width: 768px) {
  .app-footer-inner {
    flex-direction: column;
    text-align: center;
  }
}
</style>
