---
name: 高校二手物品交易平台
description: 专为高校校园打造的二手交易平台，年轻、亲切、可信赖。
colors:
  primary: "#5aa9ad"
  primary-deep: "#48878a"
  accent: "#ff6700"
  neutral-bg: "#ffffff"
  neutral-ink: "#333333"
  neutral-muted: "#949494"
  neutral-divider: "#dddddd"
typography:
  body:
    fontFamily: "Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Arial, sans-serif"
    fontSize: "14px"
    fontWeight: 400
    lineHeight: 1.5
rounded:
  sm: "4px"
  md: "10px"
  lg: "15px"
spacing:
  xs: "5px"
  sm: "10px"
  md: "20px"
components:
  button-primary:
    backgroundColor: "{colors.primary}"
    textColor: "#ffffff"
    rounded: "{rounded.sm}"
  card-product:
    backgroundColor: "{colors.neutral-bg}"
    rounded: "{colors.rounded.lg}"
---

# Design System: 高校二手物品交易平台

## 1. Overview

**Creative North Star: "校园市集"**

就像大学校园里周末的跳蚤市场——阳光穿过树荫洒在摊位上，青绿色的遮阳棚下摆满了学长学姐的闲置好物。亲切、有温度、充满青春气息。这个设计系统拒绝企业 OA 的冰冷，也不走幼稚卡通路线；它在 Element UI 的骨架之上注入校园的呼吸感。

**Key Characteristics:**
- 青绿主色调传递校园的清新与活力
- 轻微阴影和圆角让卡片像实物一样"可触碰"
- 暖橙色在价格等关键信息上形成明确的视觉锚点
- 整体克制有礼，不抢内容的风头

## 2. Colors

受校园自然景观启发：青绿色来自林荫道的树影，暖橙色来自旧书摊的黄昏灯光。

### Primary
- **薄荷青** (#5aa9ad): 主导航栏、主要按钮、品牌标识区域。这是用户看到的第一眼颜色。

### Secondary
- **深海青** (#48878a): 品牌 logo 区域的深色背景。仅用于需要与主色形成微妙对比的品牌锚点。

### Tertiary
- **暖橙** (#ff6700): 价格标签、促销信息。仅在需要吸引注意力的交易关键信息上使用，占比 ≤5%。

### Neutral
- **纯白** (#ffffff): 页面背景、卡片背景。
- **墨灰** (#333333): 标题、重要正文。比 `#666` 更深，确保可读性。
- **雾灰** (#949494): 次要信息、辅助文案、占位符。
- **浅线灰** (#dddddd): 分割线、边框。

### Named Rules
**The 克制规则。** 暖橙是唯一的高饱和度强调色，在任一屏幕上的占比不超过 5%。它的稀缺性就是它的力量。Element UI 自带的蓝 (`#409EFF`) / 绿 (`#67C23A`) / 红 (`#F56C6C`) 仅用于各自的语义场景（信息提示 / 成功 / 错误）。

## 3. Typography

**Body Font:** Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Arial, sans-serif

**Character:** 中文友好的系统字体栈。PingFang SC 在 macOS 上呈现清秀的现代感，Microsoft YaHei 在 Windows 上保持稳重。不引入额外 web font，减少加载负担。

### Hierarchy
- **页面标题** (700, 20px, 1.4): 登录页标题、管理后台模块名。
- **卡片标题** (500, 14px, 1.5): 商品名称、列表项标题。
- **正文** (400, 14px, 1.5): 通用正文，最大行宽 75ch。
- **辅助文字** (400, 13px, 1.4, #949494): 时间戳、卖家昵称、统计标签。
- **价格数字** (600, 18px, 1.2, #ff6700): 商品价格，使用暖橙。

### Named Rules
**The 一行规则。** 商品名称超过 10 字截断为省略号。卡片空间有限，标题精确不换行。

## 4. Elevation

轻阴影策略——页面整体是扁平的，阴影只在交互时出现，模拟实物被"拿起"的微妙感受。

### Shadow Vocabulary
- **hover-float** (`box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1)`): 商品卡片 hover 时轻微浮起。Element UI 的 `shadow="hover"` 行为，保持不变。

### Named Rules
**The 扁平优先规则。** 默认状态下所有表面都是平的。阴影只在 hover / focus 时出现，是反馈信号，不是装饰。绝不在静态卡片上加阴影。

## 5. Components

### 导航栏（前台）
- **背景:** 薄荷青 (#5aa9ad)，品牌 logo 区深海青 (#48878a)
- **文字:** 白色，激活项暖黄 (#ffd04b)
- **布局:** 固定顶部，1200px 内容区居中，高度 60px
- **设计意图:** 像校园集市入口的遮阳棚——一眼就知道你到地方了

### 侧边栏（后台）
- **背景:** 深灰 (#2a2a2a)
- **文字:** 白色，激活项蓝 (#409eff)
- **hover 态:** 深蓝灰 (rgb(38, 52, 69))
- **设计意图:** 后台管理的稳重暗色——让学生管理员感觉"我在操控一个正经系统"

### 商品卡片
- **圆角:** 15px（比常规卡片更圆，传递亲切感）
- **阴影:** hover 时 `shadow="hover"`
- **内边距:** 0（图片贴边，文字区 14px 内边距）
- **结构:** 图片 208×208 → 商品名（一行截断）→ 卖家名 + 价格（暖橙、18px 加粗）

### 登录卡片
- **圆角:** 10px
- **背景:** 白色，浮在背景图上
- **宽度:** 350px，居中
- **设计意图:** 像一个干净的小窗口，让学生快速进入

### 仪表盘统计卡片
- **布局:** 4 列栅格
- **每个卡片:** 图标 + 标签 + 大数字居中
- **颜色按语义分配:** 学生蓝 / 商品红 / 订单绿 / 金额橙

### 按钮
- **主按钮:** 薄荷青背景 + 白色文字，Element UI `type="primary"`
- **警告按钮:** Element UI `type="warning"`（仅注册）
- **文字按钮:** 无背景，用于次要操作

## 6. Do's and Don'ts

### Do:
- **Do** 保持白色为主背景——像干净的课桌面，让商品图片成为焦点
- **Do** 暖橙只用于价格和促销——看到橙色就知道"这里有钱相关的事"
- **Do** 使用 15px 圆角让商品卡片看起来可触碰、有温度
- **Do** 保持 Element UI 组件的默认行为——学生已经熟悉这套交互语言
- **Do** 头部导航用薄荷青，一眼识别"这是我们的平台"

### Don't:
- **Don't** 把界面做成企业 OA——纯白+灰表格、没有任何性格的后台模板感
- **Don't** 过度花哨——拒绝荧光色、弹跳动画、大面积渐变
- **Don't** 在静态卡片上加阴影——hover 反馈才有意义
- **Don't** 把暖橙用在非交易信息上——它的稀缺性是它的力量
- **Don't** 在商品卡片中使用超过 1 行的标题——截断是规则，不是例外
