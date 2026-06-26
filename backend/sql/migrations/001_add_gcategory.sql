-- ============================================
-- Migration: Add Gcategory column to goods table
-- Run this against your MySQL database
-- ============================================

-- Step 1: Add the Gcategory column
ALTER TABLE `goods` ADD COLUMN `Gcategory` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品分类: 教材/电子/生活/服饰/运动/其他' AFTER `Gname`;

-- Step 2: Assign categories based on existing product names
-- Products with 教材/教辅/书籍/书/课/学习/JAVA/程序/设计/测试
UPDATE `goods` SET `Gcategory` = '教材' WHERE `Gname` LIKE '%教材%' OR `Gname` LIKE '%教辅%' OR `Gname` LIKE '%书%' OR `Gname` LIKE '%JAVA%' OR `Gname` LIKE '%程序%' OR `Gname` LIKE '%设计%' OR `Gname` LIKE '%测试%' OR `Gname` LIKE '%课%' OR `Gname` LIKE '%学习%';

-- Products with 电子/手机/电脑/智能/科技/数码/电器
UPDATE `goods` SET `Gcategory` = '电子' WHERE `Gname` LIKE '%电子%' OR `Gname` LIKE '%手机%' OR `Gname` LIKE '%电脑%' OR `Gname` LIKE '%智能%' OR `Gname` LIKE '%科技%' OR `Gname` LIKE '%数码%' OR `Gname` LIKE '%电器%';

-- Products with 生活/日常/家居/厨房/水壶/杯/烧饼/食品/用品
UPDATE `goods` SET `Gcategory` = '生活' WHERE `Gname` LIKE '%生活%' OR `Gname` LIKE '%日常%' OR `Gname` LIKE '%家居%' OR `Gname` LIKE '%厨房%' OR `Gname` LIKE '%水壶%' OR `Gname` LIKE '%杯%' OR `Gname` LIKE '%烧饼%' OR `Gname` LIKE '%食品%' OR `Gname` LIKE '%用品%';

-- Products with 服饰/衣服/鞋/包/帽子/裤子/裙子/好看的头像
UPDATE `goods` SET `Gcategory` = '服饰' WHERE `Gname` LIKE '%服饰%' OR `Gname` LIKE '%衣服%' OR `Gname` LIKE '%鞋%' OR `Gname` LIKE '%包%' OR `Gname` LIKE '%帽子%' OR `Gname` LIKE '%裤子%' OR `Gname` LIKE '%裙子%' OR `Gname` LIKE '%头像%';

-- Products with 运动/体育/健身/户外/球/跑步/瑜伽
UPDATE `goods` SET `Gcategory` = '运动' WHERE `Gname` LIKE '%运动%' OR `Gname` LIKE '%体育%' OR `Gname` LIKE '%健身%' OR `Gname` LIKE '%户外%' OR `Gname` LIKE '%球%' OR `Gname` LIKE '%跑步%' OR `Gname` LIKE '%瑜伽%';

-- Remaining uncategorized products get '其他'
UPDATE `goods` SET `Gcategory` = '其他' WHERE `Gcategory` IS NULL;
