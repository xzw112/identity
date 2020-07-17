/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : identity

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-07-07 11:50:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tp_main_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `tp_main_admin_user`;
CREATE TABLE `tp_main_admin_user` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `user_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `login_name` varchar(16) DEFAULT NULL COMMENT '登录名',
  `login_password` varchar(64) DEFAULT NULL COMMENT '登录密码',
  `user_contact` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `user_address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `mail` varchar(100) DEFAULT NULL COMMENT '电子邮件',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(11) DEFAULT '0' COMMENT '状态 (0启用、1禁用)',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除(0未删除、1已删除)',
  `remark` text COMMENT '备注',
  `is_admin` int(11) DEFAULT '0' COMMENT '是否是超级管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tp_main_admin_user
-- ----------------------------
INSERT INTO `tp_main_admin_user` VALUES ('2d242eed25f54bfa932ac1283fa6601f', '2', '2', 'c81e728d9d4c2f636f067f89cc14862c', '', '', '', 'da41ea515d4b4f05aaf635fd336ce38c', '4ed1abe9046047b9978a9b2c927f059a', '2020-05-27 14:46:05', null, null, '0', '0', '', null);
INSERT INTO `tp_main_admin_user` VALUES ('4ed1abe9046047b9978a9b2c927f059a', 'times', 'times', '0a35494f030e513eacb2e3c996e11a8d', '332', '', '', '30ef31f39d664c47b07ca800f24704ca', null, '2020-04-29 16:19:25', '4ed1abe9046047b9978a9b2c927f059a', '2020-07-07 11:42:42', '0', '0', '', null);
INSERT INTO `tp_main_admin_user` VALUES ('55590db9478a4eeb99dcd1e6d0b9059c', '检录员1', '21', '3c59dc048e8850243be8079a5c74d079', '', '', '', '609ed763ae3c487680771312e1e5b6e0', 'f92762da446f4259981eca83c14bc429', '2020-06-11 16:24:54', 'f92762da446f4259981eca83c14bc429', '2020-06-12 10:25:49', '0', '0', '', null);
INSERT INTO `tp_main_admin_user` VALUES ('790e91721e3249daa7563492a13c3a0c', '棒球中心1', '11', '6512bd43d9caa6e02c990b0a82652dca', '', '', '', 'cf4b887894544f4ebab883e55f508896', 'f92762da446f4259981eca83c14bc429', '2020-06-03 19:33:36', 'f92762da446f4259981eca83c14bc429', '2020-06-12 10:26:05', '0', '0', '', null);
INSERT INTO `tp_main_admin_user` VALUES ('95b991ba15b948c5b094b6269a7ba517', '', '10', 'd3d9446802a44259755d38e6d163e820', '', '', '', 'da41ea515d4b4f05aaf635fd336ce38c', '4ed1abe9046047b9978a9b2c927f059a', '2020-06-03 19:03:41', null, null, '0', '0', '', null);
INSERT INTO `tp_main_admin_user` VALUES ('9d480091a1114868b681ff0c15671c15', '3', '3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '', '', '', 'da41ea515d4b4f05aaf635fd336ce38c', '4ed1abe9046047b9978a9b2c927f059a', '2020-05-27 14:48:08', null, null, '0', '0', '', null);
INSERT INTO `tp_main_admin_user` VALUES ('9feba90845f24c3e9fd76b3d601e9cc5', '区级张三', '1', 'c4ca4238a0b923820dcc509a6f75849b', '2312-23232323', '', '', 'da41ea515d4b4f05aaf635fd336ce38c', '4ed1abe9046047b9978a9b2c927f059a', '2020-05-27 14:45:47', '9feba90845f24c3e9fd76b3d601e9cc5', '2020-06-10 15:43:22', '0', '0', '', null);
INSERT INTO `tp_main_admin_user` VALUES ('cef2ac8579f14e1c9d1544ae6c8f1e66', '检录员2', '22', 'b6d767d2f8ed5d21a44b0e5886680cb9', '', '', '', '609ed763ae3c487680771312e1e5b6e0', 'f92762da446f4259981eca83c14bc429', '2020-06-16 16:35:41', null, null, '0', '0', '', null);
INSERT INTO `tp_main_admin_user` VALUES ('eff2f48a7f3444a299e22eb8b28b971e', '检录员1', '89', '7647966b7343c29048673252e490f736', '', '', '', '30ef31f39d664c47b07ca800f24704ca', '4ed1abe9046047b9978a9b2c927f059a', '2020-06-05 16:15:14', null, null, '0', '1', '', null);
INSERT INTO `tp_main_admin_user` VALUES ('f92762da446f4259981eca83c14bc429', '管理员', 'admin', '21232f297a57a5a743894a0e4a801fc3', '', '', '', '54accf8cf8904a688a5f5ccfdd11bf0e', '4ed1abe9046047b9978a9b2c927f059a', '2020-05-27 14:45:31', 'f92762da446f4259981eca83c14bc429', '2020-06-16 14:23:33', '0', '0', '', '1');
INSERT INTO `tp_main_admin_user` VALUES ('ffddc9d924804695a2b8dac0a56cbf5e', '', '12', 'c20ad4d76fe97759aa27a0c99bff6710', '', '', '', 'cf4b887894544f4ebab883e55f508896', 'f92762da446f4259981eca83c14bc429', '2020-06-03 19:36:45', 'f92762da446f4259981eca83c14bc429', '2020-06-03 19:38:48', '0', '1', '', null);

-- ----------------------------
-- Table structure for tp_main_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `tp_main_dictionary`;
CREATE TABLE `tp_main_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dictionary_value` varchar(255) DEFAULT NULL COMMENT '字典值',
  `dictionary_class` varchar(255) DEFAULT NULL COMMENT '字典标识',
  `is_delete` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tp_main_dictionary
-- ----------------------------
INSERT INTO `tp_main_dictionary` VALUES ('1', '汉族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('2', '蒙古族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('3', '回族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('4', '藏族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('5', '维吾尔族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('6', '苗族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('7', '彝族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('8', '壮族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('9', '布依族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('10', '朝鲜族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('11', '满族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('12', '侗族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('13', '瑶族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('14', '白族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('15', '土家族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('16', '哈尼族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('17', '哈萨克族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('18', '傣族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('19', '黎族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('20', '傈僳族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('21', '佤族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('22', '畲族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('23', '高山族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('24', '拉祜族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('25', '水族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('26', '东乡族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('27', '纳西族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('28', '景颇族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('29', '柯尔克孜族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('30', '土族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('31', '达斡尔族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('32', '仫佬族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('33', '羌族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('34', '布朗族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('35', '撒拉族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('36', '毛难族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('37', '仡佬族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('38', '锡伯族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('39', '阿昌族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('40', '普米族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('41', '塔吉克族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('42', '怒族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('43', '乌孜别克族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('44', '俄罗斯族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('45', '鄂温克族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('46', '崩龙族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('47', '保安族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('48', '裕固族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('49', '京族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('50', '塔塔尔族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('51', '独龙族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('52', '鄂伦春族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('53', '赫哲族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('54', '门巴族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('55', '珞巴族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('56', '基诺族', 'MZ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('57', '北京市', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('58', '天津市', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('59', '河北省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('60', '山西省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('61', '内蒙古自治区', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('62', '辽宁省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('63', '吉林省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('64', '黑龙江省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('65', '上海市', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('66', '江苏省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('67', '浙江省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('68', '安徽省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('69', '福建省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('70', '江西省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('71', '山东省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('72', '河南省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('73', '河南省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('74', '湖南省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('75', '广东省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('76', '广西壮族自治区', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('77', '海南省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('78', '重庆市', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('79', '四川省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('80', '贵州省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('81', '云南省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('82', '西藏自治区', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('83', '陕西省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('84', '甘肃省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('85', '青海省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('86', '宁夏回族自治区', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('87', '新疆维吾尔自治区', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('88', '台湾省', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('89', '香港特别行政区', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('90', '澳门特别行政区', 'JG', '0');
INSERT INTO `tp_main_dictionary` VALUES ('91', '英国', 'GJ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('92', '美国', 'GJ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('93', '韩国', 'GJ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('94', '香港', 'DQ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('95', '澳门', 'DQ', '0');
INSERT INTO `tp_main_dictionary` VALUES ('96', '台湾', 'DQ', '0');

-- ----------------------------
-- Table structure for tp_main_file
-- ----------------------------
DROP TABLE IF EXISTS `tp_main_file`;
CREATE TABLE `tp_main_file` (
  `file_id` varchar(255) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `file_time` datetime DEFAULT NULL,
  `file_storage_name` varchar(255) DEFAULT NULL,
  `file_MD5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tp_main_file
-- ----------------------------
INSERT INTO `tp_main_file` VALUES ('00b8ed66c33a496a9446237d0db0ea77', '00b8ed66c33a496a9446237d0db0ea77', '00b8ed66c33a496a9446237d0db0ea77', '2020-07-01 14:33:01', '00b8ed66c33a496a9446237d0db0ea77.jpg', '00b8ed66c33a496a9446237d0db0ea77');
INSERT INTO `tp_main_file` VALUES ('01ac804b43084606be7e7bb47b30c72b', 'timg.jpg', 'image/jpeg', '2020-07-02 14:18:08', '754442bf84474769b0ebbc65645cb8f8.jpg', '3788e6c1e7baa60d62492ba72149499f');
INSERT INTO `tp_main_file` VALUES ('03b15d2171df45059387b51f3e734c1c', '荷花.jpg', 'image/jpeg', '2020-07-02 11:51:21', 'e2fef2c5e04c4c64abe76b392ce00e42.jpg', 'e36cde999f552467f9c022c974566d00');
INSERT INTO `tp_main_file` VALUES ('0687fd75a2a0414d8919c8d4f826feeb', '0687fd75a2a0414d8919c8d4f826feeb', '0687fd75a2a0414d8919c8d4f826feeb', '2020-07-01 14:35:31', '0687fd75a2a0414d8919c8d4f826feeb.jpg', '0687fd75a2a0414d8919c8d4f826feeb');
INSERT INTO `tp_main_file` VALUES ('12e1cd6ce93946bcab0ba50a12db82ff', 'emojo.jpg', 'image/jpeg', '2020-07-02 11:54:00', '427ed8e69cc6405eb14e9f91cce77eb3.jpg', '8dd665fe651ebdf5b72b7d8fc6960a3d');
INSERT INTO `tp_main_file` VALUES ('14ffe8afa6f14d309cecb7b18f91985c', '14ffe8afa6f14d309cecb7b18f91985c', '14ffe8afa6f14d309cecb7b18f91985c', '2020-07-01 14:30:07', '14ffe8afa6f14d309cecb7b18f91985c.jpg', '14ffe8afa6f14d309cecb7b18f91985c');
INSERT INTO `tp_main_file` VALUES ('184b86c50a794c1798b1c5d31b5c2cd9', 'sleep.jpg', 'image/jpeg', '2020-07-01 14:32:16', '17643ff11bc14f5f86ee31af4da57549.jpg', '35e051735129320874bf64467c22e659');
INSERT INTO `tp_main_file` VALUES ('1d3bf80e7fd143d19922ce0d0ac3a835', '1d3bf80e7fd143d19922ce0d0ac3a835', '1d3bf80e7fd143d19922ce0d0ac3a835', '2020-07-01 14:31:41', '1d3bf80e7fd143d19922ce0d0ac3a835.jpg', '1d3bf80e7fd143d19922ce0d0ac3a835');
INSERT INTO `tp_main_file` VALUES ('20bf14676894489493f13c2efc31400a', '爱心.jpg', 'image/jpeg', '2020-07-02 18:00:01', '351a35d14c3644cdb2b010c177cdc02a.jpg', '8b7f18529a82ec78976fa25905ac19c3');
INSERT INTO `tp_main_file` VALUES ('2f51f2647da247e18deccfb592b2f897', '2f51f2647da247e18deccfb592b2f897', '2f51f2647da247e18deccfb592b2f897', '2020-07-01 14:34:01', '2f51f2647da247e18deccfb592b2f897.jpg', '2f51f2647da247e18deccfb592b2f897');
INSERT INTO `tp_main_file` VALUES ('36d5eebdd648458f966f64f0a62eff3b', '36d5eebdd648458f966f64f0a62eff3b', '36d5eebdd648458f966f64f0a62eff3b', '2020-07-01 14:30:25', '36d5eebdd648458f966f64f0a62eff3b.jpg', '36d5eebdd648458f966f64f0a62eff3b');
INSERT INTO `tp_main_file` VALUES ('38f908a858004c58a89acbb31f295bac', '38f908a858004c58a89acbb31f295bac', '38f908a858004c58a89acbb31f295bac', '2020-07-01 15:24:22', '38f908a858004c58a89acbb31f295bac.jpg', '38f908a858004c58a89acbb31f295bac');
INSERT INTO `tp_main_file` VALUES ('3f116a71e3354ecda4a2ec6f9340a15b', '3f116a71e3354ecda4a2ec6f9340a15b', '3f116a71e3354ecda4a2ec6f9340a15b', '2020-07-01 14:49:19', '3f116a71e3354ecda4a2ec6f9340a15b.jpg', '3f116a71e3354ecda4a2ec6f9340a15b');
INSERT INTO `tp_main_file` VALUES ('46c6f964ecae407f9d1955b40e6c52dc', '46c6f964ecae407f9d1955b40e6c52dc', '46c6f964ecae407f9d1955b40e6c52dc', '2020-07-01 14:29:00', '46c6f964ecae407f9d1955b40e6c52dc.jpg', '46c6f964ecae407f9d1955b40e6c52dc');
INSERT INTO `tp_main_file` VALUES ('49eec777d4564bb2962800e79293b21e', '49eec777d4564bb2962800e79293b21e', '49eec777d4564bb2962800e79293b21e', '2020-07-01 14:37:37', '49eec777d4564bb2962800e79293b21e.jpg', '49eec777d4564bb2962800e79293b21e');
INSERT INTO `tp_main_file` VALUES ('4fc6560d3046490585eb984170cc8098', '精伦.png', 'application/x-zip-compressed', '2020-07-02 16:30:16', 'bf2017c30e63475d90a4132db2623917.png', '707aad4d9552528bd75f0a4a9a3a41e8');
INSERT INTO `tp_main_file` VALUES ('516202ede495412f9a534479664e516e', '爱心.jpg', 'image/jpeg', '2020-07-01 15:09:09', '351a35d14c3644cdb2b010c177cdc02a.jpg', '8b7f18529a82ec78976fa25905ac19c3');
INSERT INTO `tp_main_file` VALUES ('52e1d6e107c543fba07974579428c602', 'emojo.jpg', 'image/jpeg', '2020-07-01 15:08:42', '427ed8e69cc6405eb14e9f91cce77eb3.jpg', '8dd665fe651ebdf5b72b7d8fc6960a3d');
INSERT INTO `tp_main_file` VALUES ('556be4f69c2c43e99467f0c198744f35', '556be4f69c2c43e99467f0c198744f35', '556be4f69c2c43e99467f0c198744f35', '2020-07-01 14:27:07', '556be4f69c2c43e99467f0c198744f35.jpg', '556be4f69c2c43e99467f0c198744f35');
INSERT INTO `tp_main_file` VALUES ('6274d49c53194fc2ae0d50bb7d81ca90', '绘画.jpg', 'image/jpeg', '2020-07-02 11:53:29', '6ca47fd1f3524792921b03d107354002.jpg', 'dee9e779b97af4acc53bc80dc274c084');
INSERT INTO `tp_main_file` VALUES ('635d9d2b30334ad482e5319b58a28c2f', '爱心.jpg', 'image/jpeg', '2020-07-02 11:53:38', '351a35d14c3644cdb2b010c177cdc02a.jpg', '8b7f18529a82ec78976fa25905ac19c3');
INSERT INTO `tp_main_file` VALUES ('6b8b3bf9a67d46ebb7684d070e9c2106', 'timg.jpg', 'image/jpeg', '2020-07-02 11:51:51', '754442bf84474769b0ebbc65645cb8f8.jpg', '3788e6c1e7baa60d62492ba72149499f');
INSERT INTO `tp_main_file` VALUES ('6ee8b0e64adc4415ae73713708786359', 'identity.png', 'image/png', '2020-07-02 17:59:22', 'e5c5fa06875549acad4555e07eda2b67.png', 'efcac0f848e1b38d98948c6d5941947e');
INSERT INTO `tp_main_file` VALUES ('701cbf96f8754a29ae83e231191ec45c', 'timg.jpg', 'image/jpeg', '2020-07-01 15:08:46', '754442bf84474769b0ebbc65645cb8f8.jpg', '3788e6c1e7baa60d62492ba72149499f');
INSERT INTO `tp_main_file` VALUES ('7ab543249e65452cbe875560ab86adc3', '7ab543249e65452cbe875560ab86adc3', '7ab543249e65452cbe875560ab86adc3', '2020-07-01 15:08:35', '7ab543249e65452cbe875560ab86adc3.jpg', '7ab543249e65452cbe875560ab86adc3');
INSERT INTO `tp_main_file` VALUES ('83dea2429a0d4dbbb5e675303f251668', 'timg.jpg', 'image/jpeg', '2020-07-01 14:32:42', '754442bf84474769b0ebbc65645cb8f8.jpg', '3788e6c1e7baa60d62492ba72149499f');
INSERT INTO `tp_main_file` VALUES ('9294dc4a61214c27ab68eb788f876ee1', '动物.jpg', 'image/jpeg', '2020-07-02 11:51:27', 'd570662968834dd9a13c841d24e4bc2a.jpg', '88113eed611649151f39788f48066785');
INSERT INTO `tp_main_file` VALUES ('9b09a29fe696435493f821730f5ed048', 'timg.jpg', 'image/jpeg', '2020-07-02 14:18:44', '754442bf84474769b0ebbc65645cb8f8.jpg', '3788e6c1e7baa60d62492ba72149499f');
INSERT INTO `tp_main_file` VALUES ('9bc6ee0972f5494ba7360d10afe731df', '微信图片_20200514142647.png', 'image/png', '2020-07-01 18:21:32', '9d51e9bd8f73487d9ff225db0bb83157.png', '5ba32bca6cd9e4a00990fce549ab29ac');
INSERT INTO `tp_main_file` VALUES ('ac40d5b251924fc2976b74bcd2f9600c', '精伦.png', 'application/x-zip-compressed', '2020-07-02 16:31:41', 'bf2017c30e63475d90a4132db2623917.png', '707aad4d9552528bd75f0a4a9a3a41e8');
INSERT INTO `tp_main_file` VALUES ('b5c5c00567134396af0f0793dc0a171a', 'b5c5c00567134396af0f0793dc0a171a', 'b5c5c00567134396af0f0793dc0a171a', '2020-07-01 14:28:29', 'b5c5c00567134396af0f0793dc0a171a.jpg', 'b5c5c00567134396af0f0793dc0a171a');
INSERT INTO `tp_main_file` VALUES ('b689187550a847cb9b2780bbfce23b3a', '爱心.jpg', 'image/jpeg', '2020-07-02 14:18:20', '351a35d14c3644cdb2b010c177cdc02a.jpg', '8b7f18529a82ec78976fa25905ac19c3');
INSERT INTO `tp_main_file` VALUES ('c14a33c89c814a15b0203629cda18303', 'rabbit.jpg', 'image/jpeg', '2020-07-01 14:31:52', '40fc16262b0a4791862d8aa4ea066c98.jpg', 'acf3fb692f4e2e9d9e2f0300c1567508');
INSERT INTO `tp_main_file` VALUES ('c4f20ea53a16492d881663b801413acf', '爱心.jpg', 'image/jpeg', '2020-07-02 14:31:33', '351a35d14c3644cdb2b010c177cdc02a.jpg', '8b7f18529a82ec78976fa25905ac19c3');
INSERT INTO `tp_main_file` VALUES ('c56a0a2c55da4f068c97e6706bb29f83', 'timg.jpg', 'image/jpeg', '2020-07-02 14:31:04', '754442bf84474769b0ebbc65645cb8f8.jpg', '3788e6c1e7baa60d62492ba72149499f');
INSERT INTO `tp_main_file` VALUES ('c9bdf88972104b53ac98afa01a403603', '爱心.jpg', 'image/jpeg', '2020-07-01 18:21:25', '351a35d14c3644cdb2b010c177cdc02a.jpg', '8b7f18529a82ec78976fa25905ac19c3');
INSERT INTO `tp_main_file` VALUES ('dca0ca7527274e19b98a47632d43b763', '爱心.jpg', 'image/jpeg', '2020-07-02 14:31:13', '351a35d14c3644cdb2b010c177cdc02a.jpg', '8b7f18529a82ec78976fa25905ac19c3');
INSERT INTO `tp_main_file` VALUES ('df07ae3a90304a459434a9eef42f36bb', 'df07ae3a90304a459434a9eef42f36bb', 'df07ae3a90304a459434a9eef42f36bb', '2020-07-01 14:50:07', 'df07ae3a90304a459434a9eef42f36bb.jpg', 'df07ae3a90304a459434a9eef42f36bb');
INSERT INTO `tp_main_file` VALUES ('df18f2726cd9488f81455741922e8008', 'df18f2726cd9488f81455741922e8008', 'df18f2726cd9488f81455741922e8008', '2020-07-01 18:14:33', 'df18f2726cd9488f81455741922e8008.jpg', 'df18f2726cd9488f81455741922e8008');
INSERT INTO `tp_main_file` VALUES ('ef5cbd0582a74b36995c82a96bc3a57c', 'ef5cbd0582a74b36995c82a96bc3a57c', 'ef5cbd0582a74b36995c82a96bc3a57c', '2020-07-01 14:54:41', 'ef5cbd0582a74b36995c82a96bc3a57c.jpg', 'ef5cbd0582a74b36995c82a96bc3a57c');
INSERT INTO `tp_main_file` VALUES ('f23a53a825a34d0097d0e0d224c323b9', 'rabbit.jpg', 'image/jpeg', '2020-07-02 17:59:32', '40fc16262b0a4791862d8aa4ea066c98.jpg', 'acf3fb692f4e2e9d9e2f0300c1567508');
INSERT INTO `tp_main_file` VALUES ('f29ce126eff54017958edc7673efa6ca', 'f29ce126eff54017958edc7673efa6ca', 'f29ce126eff54017958edc7673efa6ca', '2020-07-01 14:55:10', 'f29ce126eff54017958edc7673efa6ca.jpg', 'f29ce126eff54017958edc7673efa6ca');
INSERT INTO `tp_main_file` VALUES ('fa0dbbf34dc543d1a5802c377a7d1085', '爱心.jpg', 'image/jpeg', '2020-07-01 18:21:57', '351a35d14c3644cdb2b010c177cdc02a.jpg', '8b7f18529a82ec78976fa25905ac19c3');

-- ----------------------------
-- Table structure for tp_main_permission
-- ----------------------------
DROP TABLE IF EXISTS `tp_main_permission`;
CREATE TABLE `tp_main_permission` (
  `id` int(11) NOT NULL COMMENT '主键',
  `permission_name` varchar(16) DEFAULT '' COMMENT '权限名称',
  `permission_url` varchar(64) DEFAULT '' COMMENT 'url路径',
  `parent_id` varchar(32) DEFAULT '' COMMENT '父级id',
  `create_user` varchar(32) DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除(0未删除、1已删除)',
  `order_number` varchar(8) DEFAULT '' COMMENT '排序号',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tp_main_permission
-- ----------------------------
INSERT INTO `tp_main_permission` VALUES ('1', '菜单权限', 'admin', '', null, null, null, null, '0', '1', null);
INSERT INTO `tp_main_permission` VALUES ('2', '组织架构', 'organization_index', '1', null, null, null, null, '0', '2', null);
INSERT INTO `tp_main_permission` VALUES ('9', '人员管理', 'adminUser_index', '2', '', null, '', null, '0', '9', '');
INSERT INTO `tp_main_permission` VALUES ('10', '新建', 'adminUser_add', '9', '', null, '', null, '0', '10', '');
INSERT INTO `tp_main_permission` VALUES ('11', '编辑', 'adminUser_edit', '9', '', null, '', null, '0', '11', '');
INSERT INTO `tp_main_permission` VALUES ('12', '删除', 'adminUser_del', '9', '', null, '', null, '0', '12', '');
INSERT INTO `tp_main_permission` VALUES ('13', '启用', 'adminUser_enable', '9', '', null, '', null, '0', '13', '');
INSERT INTO `tp_main_permission` VALUES ('14', '禁用', 'adminUser_disable', '9', '', null, '', null, '0', '14', '');
INSERT INTO `tp_main_permission` VALUES ('15', '角色管理', 'role_index', '2', '', null, '', null, '0', '15', '');
INSERT INTO `tp_main_permission` VALUES ('16', '新建', 'role_add', '15', '', null, '', null, '0', '16', '');
INSERT INTO `tp_main_permission` VALUES ('17', '编辑', 'role_edit', '15', '', null, '', null, '0', '17', '');
INSERT INTO `tp_main_permission` VALUES ('18', '删除', 'role_del', '15', '', null, '', null, '0', '18', '');
INSERT INTO `tp_main_permission` VALUES ('19', '启用', 'role_enable', '15', '', null, '', null, '0', '19', '');
INSERT INTO `tp_main_permission` VALUES ('20', '禁用', 'role_disable', '15', '', null, '', null, '0', '20', '');
INSERT INTO `tp_main_permission` VALUES ('21', '权限设置', 'role_set_auth', '15', '', null, '', null, '0', '21', '');
INSERT INTO `tp_main_permission` VALUES ('24', '个人中心', 'personal_index', '1', '', null, '', null, '0', '24', '');
INSERT INTO `tp_main_permission` VALUES ('25', '个人资料', 'personalInfo_index', '24', '', null, '', null, '0', '25', '');
INSERT INTO `tp_main_permission` VALUES ('26', '修改密码', 'modifyPassword_index', '24', '', null, '', null, '0', '26', '');
INSERT INTO `tp_main_permission` VALUES ('27', '系统设置', 'sysConfig_index', '1', '', null, '', null, '0', '27', '');
INSERT INTO `tp_main_permission` VALUES ('32', '操作日志', 'systemLog_index', '27', '', null, '', null, '0', '32', '');

-- ----------------------------
-- Table structure for tp_main_role
-- ----------------------------
DROP TABLE IF EXISTS `tp_main_role`;
CREATE TABLE `tp_main_role` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `role_name` varchar(32) DEFAULT '' COMMENT '角色名称',
  `role_number` varchar(32) DEFAULT '' COMMENT '角色编码',
  `order_number` varchar(8) DEFAULT '' COMMENT '排序号',
  `create_user` varchar(32) DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(11) DEFAULT '0' COMMENT '状态(0启用、1禁用)',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除(0未删除、1已删除)',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tp_main_role
-- ----------------------------
INSERT INTO `tp_main_role` VALUES ('0fe4c04c66ac404396b92b0cbebac01b', '434434', null, null, '4ed1abe9046047b9978a9b2c927f059a', '2020-05-08 17:25:50', null, null, '0', '1', '43');
INSERT INTO `tp_main_role` VALUES ('229d160ee39044b8a723dc64f5a270a4', '223', null, null, '4ed1abe9046047b9978a9b2c927f059a', '2020-05-15 11:20:18', null, null, '0', '1', '323');
INSERT INTO `tp_main_role` VALUES ('2b40bd8babc14f9ca426a9b9f97d07ee', '34', null, null, '4ed1abe9046047b9978a9b2c927f059a', '2020-05-21 09:16:42', '4ed1abe9046047b9978a9b2c927f059a', '2020-05-21 09:16:46', '0', '1', '544545');
INSERT INTO `tp_main_role` VALUES ('30ef31f39d664c47b07ca800f24704ca', '体育局角色', null, null, '4ed1abe9046047b9978a9b2c927f059a', '2020-04-30 13:15:43', '4ed1abe9046047b9978a9b2c927f059a', '2020-05-27 15:03:46', '0', '0', '审核运动员信息');
INSERT INTO `tp_main_role` VALUES ('446e54bf15a14feeb0939ba0f98929e9', '232', null, null, '4ed1abe9046047b9978a9b2c927f059a', '2020-05-09 10:01:44', '4ed1abe9046047b9978a9b2c927f059a', '2020-05-09 10:01:48', '0', '1', '');
INSERT INTO `tp_main_role` VALUES ('54accf8cf8904a688a5f5ccfdd11bf0e', '管理员角色', null, null, '4ed1abe9046047b9978a9b2c927f059a', '2020-05-27 15:03:36', null, null, '0', '0', '顶级权限，拥有所有权限');
INSERT INTO `tp_main_role` VALUES ('609ed763ae3c487680771312e1e5b6e0', '检录角色', null, null, 'f92762da446f4259981eca83c14bc429', '2020-06-11 16:22:20', null, null, '0', '0', '只用于检录');
INSERT INTO `tp_main_role` VALUES ('a7e90e2a891b487ebb55dfc338f44a6f', '434', null, null, '4ed1abe9046047b9978a9b2c927f059a', '2020-05-06 17:52:07', null, null, '0', '1', '34');
INSERT INTO `tp_main_role` VALUES ('b0f5f9fa496a41c4b61d415bddedd68c', '测试角色', null, null, '4ed1abe9046047b9978a9b2c927f059a', '2020-05-12 15:51:50', null, null, '1', '1', '12');
INSERT INTO `tp_main_role` VALUES ('b6dd72d1b8a146809f4280fcd4d048e0', '343', null, null, '4ed1abe9046047b9978a9b2c927f059a', '2020-05-09 17:44:43', '4ed1abe9046047b9978a9b2c927f059a', '2020-05-09 17:44:49', '0', '1', '43');
INSERT INTO `tp_main_role` VALUES ('cf4b887894544f4ebab883e55f508896', '中心角色', null, null, '4ed1abe9046047b9978a9b2c927f059a', '2020-05-08 14:09:32', null, null, '0', '0', '可查看项目');
INSERT INTO `tp_main_role` VALUES ('da41ea515d4b4f05aaf635fd336ce38c', '区级角色', null, null, '4ed1abe9046047b9978a9b2c927f059a', '2020-04-30 13:17:31', '4ed1abe9046047b9978a9b2c927f059a', '2020-06-11 14:27:10', '0', '0', '可添加运动员');
INSERT INTO `tp_main_role` VALUES ('f1780bd1493641bd90eae34fcfb64358', '6767', null, null, '4ed1abe9046047b9978a9b2c927f059a', '2020-05-06 17:52:19', null, null, '0', '1', '215');

-- ----------------------------
-- Table structure for tp_main_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tp_main_role_permission`;
CREATE TABLE `tp_main_role_permission` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `role_id` varchar(32) DEFAULT '' COMMENT '角色id',
  `permission_id` varchar(32) DEFAULT NULL COMMENT '权限id',
  `id_attribute` varchar(8) DEFAULT NULL COMMENT '判断权限属性是否为未全选的父节点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tp_main_role_permission
-- ----------------------------
INSERT INTO `tp_main_role_permission` VALUES ('0014c9953fdc4160b794054211d9fc15', 'da41ea515d4b4f05aaf635fd336ce38c', '151', '');
INSERT INTO `tp_main_role_permission` VALUES ('00424c3f9245456fa9f99421bb16cfd3', 'da41ea515d4b4f05aaf635fd336ce38c', '56', '');
INSERT INTO `tp_main_role_permission` VALUES ('00768b2f05204f41bc6c97d77502a343', '54accf8cf8904a688a5f5ccfdd11bf0e', '90', '');
INSERT INTO `tp_main_role_permission` VALUES ('00e164d8a1d04f31b3396232646518dd', '54accf8cf8904a688a5f5ccfdd11bf0e', '112', '');
INSERT INTO `tp_main_role_permission` VALUES ('02aabc54b6c04833a44c192513bd6c39', '54accf8cf8904a688a5f5ccfdd11bf0e', '20', '');
INSERT INTO `tp_main_role_permission` VALUES ('031cc77496f5465f8f431cd65b81bbca', 'da41ea515d4b4f05aaf635fd336ce38c', '58', '');
INSERT INTO `tp_main_role_permission` VALUES ('06b28b3079324a639776acdc308c2af6', '54accf8cf8904a688a5f5ccfdd11bf0e', '159', '');
INSERT INTO `tp_main_role_permission` VALUES ('083caa9ba2da41ae93214d402063ca89', '54accf8cf8904a688a5f5ccfdd11bf0e', '169', '');
INSERT INTO `tp_main_role_permission` VALUES ('084ce7451cd94f9cb8700bcdbb01e360', 'da41ea515d4b4f05aaf635fd336ce38c', '162', '');
INSERT INTO `tp_main_role_permission` VALUES ('08c72342d3fb443fba8cdf901bfec1f0', '54accf8cf8904a688a5f5ccfdd11bf0e', '157', '');
INSERT INTO `tp_main_role_permission` VALUES ('0a3a0298432140f29379abc454f4fbf3', '54accf8cf8904a688a5f5ccfdd11bf0e', '41', '');
INSERT INTO `tp_main_role_permission` VALUES ('0b014e6054394bdeb34c379ddead8025', '54accf8cf8904a688a5f5ccfdd11bf0e', '160', '');
INSERT INTO `tp_main_role_permission` VALUES ('0be10d93628c4b74b5f9a35bfdb6d57c', '609ed763ae3c487680771312e1e5b6e0', '15', '');
INSERT INTO `tp_main_role_permission` VALUES ('0bef9c4818c84912ae9d11a98cbb4e25', '54accf8cf8904a688a5f5ccfdd11bf0e', '80', '');
INSERT INTO `tp_main_role_permission` VALUES ('0c8c0f51283e4126b0dbea3bdb8fffb0', 'da41ea515d4b4f05aaf635fd336ce38c', '108', '');
INSERT INTO `tp_main_role_permission` VALUES ('0d0bea80fa59438b99231e22742b5446', 'da41ea515d4b4f05aaf635fd336ce38c', '121', '');
INSERT INTO `tp_main_role_permission` VALUES ('0d26ec7a9df14fab8ad63fcb23ea8d24', 'da41ea515d4b4f05aaf635fd336ce38c', '152', '');
INSERT INTO `tp_main_role_permission` VALUES ('0f411745265346daa15e5208e8c2e88c', 'cf4b887894544f4ebab883e55f508896', '54', '');
INSERT INTO `tp_main_role_permission` VALUES ('12fc0ff2c63e4a3ca41aa0c2c047efb7', '54accf8cf8904a688a5f5ccfdd11bf0e', '140', '');
INSERT INTO `tp_main_role_permission` VALUES ('131e987c00a4473599df6f9f417e9838', '30ef31f39d664c47b07ca800f24704ca', '15', '');
INSERT INTO `tp_main_role_permission` VALUES ('13f616cb44594d8da900364cff347ea9', '54accf8cf8904a688a5f5ccfdd11bf0e', '24', '');
INSERT INTO `tp_main_role_permission` VALUES ('17ad934d9724446da15ee514daf233f1', '609ed763ae3c487680771312e1e5b6e0', '174', '');
INSERT INTO `tp_main_role_permission` VALUES ('19cb4b8d73f740c08bae9838b2436af0', '54accf8cf8904a688a5f5ccfdd11bf0e', '6', '');
INSERT INTO `tp_main_role_permission` VALUES ('1acd5824a13249bd8224b7791ab085ef', 'da41ea515d4b4f05aaf635fd336ce38c', '167', '');
INSERT INTO `tp_main_role_permission` VALUES ('1c54eaacdcd94594976f86c3117bab1f', '609ed763ae3c487680771312e1e5b6e0', '173', '');
INSERT INTO `tp_main_role_permission` VALUES ('1c9bc00ed1a145908aead69d14f8c201', '54accf8cf8904a688a5f5ccfdd11bf0e', '172', '');
INSERT INTO `tp_main_role_permission` VALUES ('1da275ff5bf045acab780261d35ac013', 'da41ea515d4b4f05aaf635fd336ce38c', '25', '');
INSERT INTO `tp_main_role_permission` VALUES ('21ffcd4c63ca45fa85e0e82ac6d6d753', '54accf8cf8904a688a5f5ccfdd11bf0e', '162', '');
INSERT INTO `tp_main_role_permission` VALUES ('245c84b16b7d46fb906597305a46e769', 'da41ea515d4b4f05aaf635fd336ce38c', '110', '');
INSERT INTO `tp_main_role_permission` VALUES ('24adbdb1f2ca4df59afc77623d3502d7', '54accf8cf8904a688a5f5ccfdd11bf0e', '22', '');
INSERT INTO `tp_main_role_permission` VALUES ('2647815959584e6aacc932cca4d402f1', '54accf8cf8904a688a5f5ccfdd11bf0e', '49', '');
INSERT INTO `tp_main_role_permission` VALUES ('26bdf948fe7749d895d72ffb496a9e5a', '54accf8cf8904a688a5f5ccfdd11bf0e', '127', '');
INSERT INTO `tp_main_role_permission` VALUES ('2749e6db97e5427799e9539aeed9ced6', '54accf8cf8904a688a5f5ccfdd11bf0e', '25', '');
INSERT INTO `tp_main_role_permission` VALUES ('27bd2cff3d934755b3e98843348c5d05', '54accf8cf8904a688a5f5ccfdd11bf0e', '164', '');
INSERT INTO `tp_main_role_permission` VALUES ('2845c77bf6d34491883b616368c29c46', '54accf8cf8904a688a5f5ccfdd11bf0e', '42', '');
INSERT INTO `tp_main_role_permission` VALUES ('28aaa3f79c01464884e2285fe5918754', 'da41ea515d4b4f05aaf635fd336ce38c', '1', 'parent');
INSERT INTO `tp_main_role_permission` VALUES ('2a0445c2f97340c18b23b5afcbce4d91', 'da41ea515d4b4f05aaf635fd336ce38c', '127', '');
INSERT INTO `tp_main_role_permission` VALUES ('2a221ce7c65646248e272ab6e0c5857a', '54accf8cf8904a688a5f5ccfdd11bf0e', '52', '');
INSERT INTO `tp_main_role_permission` VALUES ('2d26a0c4d26d4048b1a16ed1c88ea2a1', 'da41ea515d4b4f05aaf635fd336ce38c', '54', '');
INSERT INTO `tp_main_role_permission` VALUES ('2e240aee62494b11bcc3b214ba3d2f19', '609ed763ae3c487680771312e1e5b6e0', '18', '');
INSERT INTO `tp_main_role_permission` VALUES ('319c1fae32754e6cb1f2f4c1f202e569', 'da41ea515d4b4f05aaf635fd336ce38c', '156', '');
INSERT INTO `tp_main_role_permission` VALUES ('32d89ab370bb405197d0e1e533581b00', '54accf8cf8904a688a5f5ccfdd11bf0e', '119', '');
INSERT INTO `tp_main_role_permission` VALUES ('34958476fbd0425ea9986931d600249f', 'da41ea515d4b4f05aaf635fd336ce38c', '161', '');
INSERT INTO `tp_main_role_permission` VALUES ('34ca56a2b12b4e74bea0995aaf1cbea6', '54accf8cf8904a688a5f5ccfdd11bf0e', '79', '');
INSERT INTO `tp_main_role_permission` VALUES ('35339f4a839b4211b82033e73a7c6be0', 'da41ea515d4b4f05aaf635fd336ce38c', '48', '');
INSERT INTO `tp_main_role_permission` VALUES ('356251f4e33b40f7ac9484f4c525e60a', 'cf4b887894544f4ebab883e55f508896', '137', '');
INSERT INTO `tp_main_role_permission` VALUES ('35fb45463c5a4be0837a84b1ebb05863', '30ef31f39d664c47b07ca800f24704ca', '21', '');
INSERT INTO `tp_main_role_permission` VALUES ('37300c2bbe8b46b191f44c08d6a01563', 'da41ea515d4b4f05aaf635fd336ce38c', '109', '');
INSERT INTO `tp_main_role_permission` VALUES ('38832bc276cf4aecbd36f6f16cfb98b8', '30ef31f39d664c47b07ca800f24704ca', '10', '');
INSERT INTO `tp_main_role_permission` VALUES ('3917af8993f249daabcc8fc0c78f3031', 'da41ea515d4b4f05aaf635fd336ce38c', '122', 'parent');
INSERT INTO `tp_main_role_permission` VALUES ('39b3728d598b454fbf5514d8eedfe10c', '609ed763ae3c487680771312e1e5b6e0', '22', '');
INSERT INTO `tp_main_role_permission` VALUES ('3a418108e5ec4b30b8b09493cc703338', 'cf4b887894544f4ebab883e55f508896', '53', '');
INSERT INTO `tp_main_role_permission` VALUES ('3b2c1cdc1a4d425097b2753e6f07df33', '54accf8cf8904a688a5f5ccfdd11bf0e', '174', '');
INSERT INTO `tp_main_role_permission` VALUES ('3cecf9cdc459436aa5acc3b45d7ffeaf', '54accf8cf8904a688a5f5ccfdd11bf0e', '54', '');
INSERT INTO `tp_main_role_permission` VALUES ('3d432a4468994856b057fd9f9437dccc', '54accf8cf8904a688a5f5ccfdd11bf0e', '171', '');
INSERT INTO `tp_main_role_permission` VALUES ('3db4094e1cc24479816d6d01952995d8', '54accf8cf8904a688a5f5ccfdd11bf0e', '152', '');
INSERT INTO `tp_main_role_permission` VALUES ('3def48a4246344a2a525662dc3095b31', '609ed763ae3c487680771312e1e5b6e0', '1', 'parent');
INSERT INTO `tp_main_role_permission` VALUES ('3e1d5f96e5424051a3db99fded44a746', 'da41ea515d4b4f05aaf635fd336ce38c', '124', '');
INSERT INTO `tp_main_role_permission` VALUES ('3f3209fba8214873893fe95a5efe80ce', 'cf4b887894544f4ebab883e55f508896', '51', '');
INSERT INTO `tp_main_role_permission` VALUES ('3f5fabf36cf141a69f19cbb23435e406', 'da41ea515d4b4f05aaf635fd336ce38c', '128', '');
INSERT INTO `tp_main_role_permission` VALUES ('40a147219ba14668a2eab5ecb8a45542', 'da41ea515d4b4f05aaf635fd336ce38c', '53', '');
INSERT INTO `tp_main_role_permission` VALUES ('417eab44d85343b58b461f74ada38326', '54accf8cf8904a688a5f5ccfdd11bf0e', '161', '');
INSERT INTO `tp_main_role_permission` VALUES ('419cfc9bf4f84354a62193b9fc9f20b3', '54accf8cf8904a688a5f5ccfdd11bf0e', '46', '');
INSERT INTO `tp_main_role_permission` VALUES ('41e54138b1ee4bb6ae6953ff31e3ee9b', 'cf4b887894544f4ebab883e55f508896', '48', '');
INSERT INTO `tp_main_role_permission` VALUES ('43ee8e3940424444a8493c0c73c5b800', 'cf4b887894544f4ebab883e55f508896', '46', '');
INSERT INTO `tp_main_role_permission` VALUES ('443b1c89beb44e099febeb767b8a3b4d', '54accf8cf8904a688a5f5ccfdd11bf0e', '139', '');
INSERT INTO `tp_main_role_permission` VALUES ('45c98765c3d346f1ac8ff0ba98e017ba', 'da41ea515d4b4f05aaf635fd336ce38c', '113', '');
INSERT INTO `tp_main_role_permission` VALUES ('45e2e3bec3a24829b8231f0b6ac08c1a', '54accf8cf8904a688a5f5ccfdd11bf0e', '51', '');
INSERT INTO `tp_main_role_permission` VALUES ('47aa12ac246a48379cfc04ca9f2e3f6f', '54accf8cf8904a688a5f5ccfdd11bf0e', '108', '');
INSERT INTO `tp_main_role_permission` VALUES ('47bd8225131f449491010283fc52a3c9', '54accf8cf8904a688a5f5ccfdd11bf0e', '113', '');
INSERT INTO `tp_main_role_permission` VALUES ('487ce3f49eae440cb43b9a2715043804', 'da41ea515d4b4f05aaf635fd336ce38c', '138', '');
INSERT INTO `tp_main_role_permission` VALUES ('48beed1914e44cc5be605a7ca38b8304', '54accf8cf8904a688a5f5ccfdd11bf0e', '29', '');
INSERT INTO `tp_main_role_permission` VALUES ('48ebc64984df42afadd1f529ad51e554', '54accf8cf8904a688a5f5ccfdd11bf0e', '111', '');
INSERT INTO `tp_main_role_permission` VALUES ('4b1e08068e2a4b8f89cd069f948e7e44', 'da41ea515d4b4f05aaf635fd336ce38c', '166', '');
INSERT INTO `tp_main_role_permission` VALUES ('4cd769b1686c47f7ba0329233c4a8387', 'da41ea515d4b4f05aaf635fd336ce38c', '147', '');
INSERT INTO `tp_main_role_permission` VALUES ('4da2c733205e4bc0b563b318b2bf976f', 'cf4b887894544f4ebab883e55f508896', '1', 'parent');
INSERT INTO `tp_main_role_permission` VALUES ('4e9d072a65db4cfa9892cde62ef3a85b', '54accf8cf8904a688a5f5ccfdd11bf0e', '109', '');
INSERT INTO `tp_main_role_permission` VALUES ('4f051adc384f41928b382a2a22d74e09', '54accf8cf8904a688a5f5ccfdd11bf0e', '85', '');
INSERT INTO `tp_main_role_permission` VALUES ('4f536396db78419d9566f300ba8c55eb', '54accf8cf8904a688a5f5ccfdd11bf0e', '53', '');
INSERT INTO `tp_main_role_permission` VALUES ('4f8d314e1df949fdb1b18cf55483c12c', '54accf8cf8904a688a5f5ccfdd11bf0e', '168', '');
INSERT INTO `tp_main_role_permission` VALUES ('511eace711174b2e9ec680e0a0a1930c', '54accf8cf8904a688a5f5ccfdd11bf0e', '82', '');
INSERT INTO `tp_main_role_permission` VALUES ('5213641d5f844620b711dfa6e9df4b3b', '30ef31f39d664c47b07ca800f24704ca', '17', '');
INSERT INTO `tp_main_role_permission` VALUES ('53db3028ca92459fa49a37df456251f9', 'da41ea515d4b4f05aaf635fd336ce38c', '107', '');
INSERT INTO `tp_main_role_permission` VALUES ('55a52158a1564f26af0dd00f6130d7a3', 'da41ea515d4b4f05aaf635fd336ce38c', '47', '');
INSERT INTO `tp_main_role_permission` VALUES ('55e1f823c45f4ce1a639e48e4b0b4fae', '54accf8cf8904a688a5f5ccfdd11bf0e', '38', '');
INSERT INTO `tp_main_role_permission` VALUES ('56e16a0e06134fb1a73c0a1dea856059', '30ef31f39d664c47b07ca800f24704ca', '2', '');
INSERT INTO `tp_main_role_permission` VALUES ('58d2705ceb8c4cdf884786035b20db9e', '54accf8cf8904a688a5f5ccfdd11bf0e', '34', '');
INSERT INTO `tp_main_role_permission` VALUES ('593b600189f3446baad403ad86ae201c', '2b40bd8babc14f9ca426a9b9f97d07ee', '4', '');
INSERT INTO `tp_main_role_permission` VALUES ('5c410a2496b3457cbb66054bc88703f0', '609ed763ae3c487680771312e1e5b6e0', '14', '');
INSERT INTO `tp_main_role_permission` VALUES ('5c95a24393fe4e51b368c30314674b83', '54accf8cf8904a688a5f5ccfdd11bf0e', '167', '');
INSERT INTO `tp_main_role_permission` VALUES ('5cd3c7856624441c8751dcc803b898cd', 'da41ea515d4b4f05aaf635fd336ce38c', '130', '');
INSERT INTO `tp_main_role_permission` VALUES ('5d82331d899e44b4ba646e474b305d5d', '30ef31f39d664c47b07ca800f24704ca', '9', '');
INSERT INTO `tp_main_role_permission` VALUES ('5df073a9ad634a158abc3d4d03c5a646', 'da41ea515d4b4f05aaf635fd336ce38c', '115', '');
INSERT INTO `tp_main_role_permission` VALUES ('5ea6bd4d87a048738e9dacb29220ab68', '54accf8cf8904a688a5f5ccfdd11bf0e', '45', '');
INSERT INTO `tp_main_role_permission` VALUES ('61208680fe724249b0f719973c29fb71', '609ed763ae3c487680771312e1e5b6e0', '7', '');
INSERT INTO `tp_main_role_permission` VALUES ('61bff1c993364c43b08baa4e8766508b', '54accf8cf8904a688a5f5ccfdd11bf0e', '138', '');
INSERT INTO `tp_main_role_permission` VALUES ('62987dbddd0d45cf9c9e768b2001fed5', 'cf4b887894544f4ebab883e55f508896', '56', '');
INSERT INTO `tp_main_role_permission` VALUES ('632b62346fd84fa982c69015ac5b5ccd', '54accf8cf8904a688a5f5ccfdd11bf0e', '2', '');
INSERT INTO `tp_main_role_permission` VALUES ('639a6d42f80740879bb06ea0321c7239', 'da41ea515d4b4f05aaf635fd336ce38c', '145', 'parent');
INSERT INTO `tp_main_role_permission` VALUES ('63bfdedeca61472ea3537c17af09b061', '54accf8cf8904a688a5f5ccfdd11bf0e', '118', '');
INSERT INTO `tp_main_role_permission` VALUES ('686ebc4b2e4d424aa778115c52ce0951', '54accf8cf8904a688a5f5ccfdd11bf0e', '47', '');
INSERT INTO `tp_main_role_permission` VALUES ('6877a07d89dd4ba7b295cbc7d96212e4', '54accf8cf8904a688a5f5ccfdd11bf0e', '4', '');
INSERT INTO `tp_main_role_permission` VALUES ('6ae22c30f14e4dababd3158e3b165f41', 'da41ea515d4b4f05aaf635fd336ce38c', '153', '');
INSERT INTO `tp_main_role_permission` VALUES ('6b202938a1d04fa48beecf9bb6cf73ec', '54accf8cf8904a688a5f5ccfdd11bf0e', '40', '');
INSERT INTO `tp_main_role_permission` VALUES ('6b64f878427548e6bdef190198832a79', '609ed763ae3c487680771312e1e5b6e0', '12', '');
INSERT INTO `tp_main_role_permission` VALUES ('6c6cda377d204d16a82106f7b994fdf5', '54accf8cf8904a688a5f5ccfdd11bf0e', '78', '');
INSERT INTO `tp_main_role_permission` VALUES ('6d08a30416e14feb99e67b3093993fa4', '30ef31f39d664c47b07ca800f24704ca', '11', '');
INSERT INTO `tp_main_role_permission` VALUES ('6d742ceca42a4ae082c85f806e41ceb8', 'da41ea515d4b4f05aaf635fd336ce38c', '139', '');
INSERT INTO `tp_main_role_permission` VALUES ('6e53701187984f75a875b85803239700', '30ef31f39d664c47b07ca800f24704ca', '27', '');
INSERT INTO `tp_main_role_permission` VALUES ('70cea5be92b24d1c89324ee9a8e8cd00', '609ed763ae3c487680771312e1e5b6e0', '11', '');
INSERT INTO `tp_main_role_permission` VALUES ('71f3873f92ae47a3bdd6ec5569538f23', '54accf8cf8904a688a5f5ccfdd11bf0e', '148', '');
INSERT INTO `tp_main_role_permission` VALUES ('731dc02e23c54a7389e9e6ba79c0fbbf', '54accf8cf8904a688a5f5ccfdd11bf0e', '77', '');
INSERT INTO `tp_main_role_permission` VALUES ('7350974f9d014063ba372b57ad95cb96', '54accf8cf8904a688a5f5ccfdd11bf0e', '58', '');
INSERT INTO `tp_main_role_permission` VALUES ('7394836a058e4a7f9dc2a38cad9bb84a', '54accf8cf8904a688a5f5ccfdd11bf0e', '35', '');
INSERT INTO `tp_main_role_permission` VALUES ('758aa253f7bd42daa71ed288689f2102', '54accf8cf8904a688a5f5ccfdd11bf0e', '16', '');
INSERT INTO `tp_main_role_permission` VALUES ('75dc0b28c70e47b2b5bc464c62c2f53f', '2b40bd8babc14f9ca426a9b9f97d07ee', '3', 'parent');
INSERT INTO `tp_main_role_permission` VALUES ('76340cda7e8b4799a4839ea697da0f77', 'da41ea515d4b4f05aaf635fd336ce38c', '26', '');
INSERT INTO `tp_main_role_permission` VALUES ('766221e6689043c7abbaa590d9b46361', '54accf8cf8904a688a5f5ccfdd11bf0e', '141', '');
INSERT INTO `tp_main_role_permission` VALUES ('78a0d6d0a7474673ba5b579032925fc0', '54accf8cf8904a688a5f5ccfdd11bf0e', '23', '');
INSERT INTO `tp_main_role_permission` VALUES ('79dd1ad437134247baf7a3606127e249', '54accf8cf8904a688a5f5ccfdd11bf0e', '114', '');
INSERT INTO `tp_main_role_permission` VALUES ('7b10b7db16764647bf6a4830519f6aa1', '54accf8cf8904a688a5f5ccfdd11bf0e', '165', '');
INSERT INTO `tp_main_role_permission` VALUES ('7b343abb188145b8ade178f2c659c84f', '54accf8cf8904a688a5f5ccfdd11bf0e', '48', '');
INSERT INTO `tp_main_role_permission` VALUES ('7bf14bbdf2764d429e35b0b7276fd0f9', 'da41ea515d4b4f05aaf635fd336ce38c', '106', '');
INSERT INTO `tp_main_role_permission` VALUES ('7d0274eb67db4abbb3630b4347622502', '609ed763ae3c487680771312e1e5b6e0', '2', '');
INSERT INTO `tp_main_role_permission` VALUES ('7dcd32a583a84a569abd3a3030a50c70', 'da41ea515d4b4f05aaf635fd336ce38c', '123', 'parent');
INSERT INTO `tp_main_role_permission` VALUES ('821cf1fe69324713905112760004d27c', '609ed763ae3c487680771312e1e5b6e0', '8', '');
INSERT INTO `tp_main_role_permission` VALUES ('828f17388c1a48979324a018a6a2e5d1', 'da41ea515d4b4f05aaf635fd336ce38c', '136', '');
INSERT INTO `tp_main_role_permission` VALUES ('830fbf4512464ba2baf97f69343a5582', '54accf8cf8904a688a5f5ccfdd11bf0e', '121', '');
INSERT INTO `tp_main_role_permission` VALUES ('867dd8072ccc43569ba689a9f31b4d31', '54accf8cf8904a688a5f5ccfdd11bf0e', '43', '');
INSERT INTO `tp_main_role_permission` VALUES ('877c9c8bd5264dfda0e18ae64dcc3af4', 'da41ea515d4b4f05aaf635fd336ce38c', '163', '');
INSERT INTO `tp_main_role_permission` VALUES ('87a9b3fa3b054596999ef05541d26e59', 'da41ea515d4b4f05aaf635fd336ce38c', '137', '');
INSERT INTO `tp_main_role_permission` VALUES ('887715cf363e41f09a0df307550eb10b', 'cf4b887894544f4ebab883e55f508896', '122', 'parent');
INSERT INTO `tp_main_role_permission` VALUES ('89556b0320474521a907d1b5f056afe8', '30ef31f39d664c47b07ca800f24704ca', '14', '');
INSERT INTO `tp_main_role_permission` VALUES ('8a60d690b7424f85a346341bb80e7f27', '54accf8cf8904a688a5f5ccfdd11bf0e', '1', '');
INSERT INTO `tp_main_role_permission` VALUES ('8a9c5d3322dc4080ba75dc8989837e36', '54accf8cf8904a688a5f5ccfdd11bf0e', '37', '');
INSERT INTO `tp_main_role_permission` VALUES ('8ad6e84b0e5642fe9fe7fa98f7fb8076', '54accf8cf8904a688a5f5ccfdd11bf0e', '137', '');
INSERT INTO `tp_main_role_permission` VALUES ('8af8e87c7f92408caa289ebf29f0d757', '54accf8cf8904a688a5f5ccfdd11bf0e', '146', '');
INSERT INTO `tp_main_role_permission` VALUES ('8bda551f61754b4a9a7b09235a6fecf2', '54accf8cf8904a688a5f5ccfdd11bf0e', '124', '');
INSERT INTO `tp_main_role_permission` VALUES ('8c5c6003f91a4479bf7e0a2528d2d9c2', '609ed763ae3c487680771312e1e5b6e0', '172', '');
INSERT INTO `tp_main_role_permission` VALUES ('8cb2a658acfe4d93b1981832ef8abb76', 'da41ea515d4b4f05aaf635fd336ce38c', '144', '');
INSERT INTO `tp_main_role_permission` VALUES ('8f752df7aec7426eb8d24195c018a74b', 'da41ea515d4b4f05aaf635fd336ce38c', '49', '');
INSERT INTO `tp_main_role_permission` VALUES ('8fd25c5be657432cb8a8c4eb353578d1', '609ed763ae3c487680771312e1e5b6e0', '6', '');
INSERT INTO `tp_main_role_permission` VALUES ('9048480895b9419eba6e0d07d088f6f7', '54accf8cf8904a688a5f5ccfdd11bf0e', '27', '');
INSERT INTO `tp_main_role_permission` VALUES ('90be80d74c4f49eba516856f0f0755c2', 'da41ea515d4b4f05aaf635fd336ce38c', '154', '');
INSERT INTO `tp_main_role_permission` VALUES ('91285375e0544f30b303202ffed73d20', '609ed763ae3c487680771312e1e5b6e0', '16', '');
INSERT INTO `tp_main_role_permission` VALUES ('91c91814a9114d85bee123c91f4b8837', '54accf8cf8904a688a5f5ccfdd11bf0e', '39', '');
INSERT INTO `tp_main_role_permission` VALUES ('9248f0f1da4c4f47b5359f624e00e3f3', '54accf8cf8904a688a5f5ccfdd11bf0e', '84', '');
INSERT INTO `tp_main_role_permission` VALUES ('93649948725941fa9388766e9712c5d9', '54accf8cf8904a688a5f5ccfdd11bf0e', '8', '');
INSERT INTO `tp_main_role_permission` VALUES ('946e5038406c4920addfc3df530e0be4', '30ef31f39d664c47b07ca800f24704ca', '32', '');
INSERT INTO `tp_main_role_permission` VALUES ('9476ca854e8c40c2b44ec9f6d327db60', '54accf8cf8904a688a5f5ccfdd11bf0e', '81', '');
INSERT INTO `tp_main_role_permission` VALUES ('95019d3a86194eb89d995c27a3df1e87', '54accf8cf8904a688a5f5ccfdd11bf0e', '142', '');
INSERT INTO `tp_main_role_permission` VALUES ('9646cbffc785423c93f0e45619e0e03d', '609ed763ae3c487680771312e1e5b6e0', '20', '');
INSERT INTO `tp_main_role_permission` VALUES ('97753277ee9749999bf46f17a222f877', '54accf8cf8904a688a5f5ccfdd11bf0e', '26', '');
INSERT INTO `tp_main_role_permission` VALUES ('98b126a6c69e48aea46538012698b68a', '54accf8cf8904a688a5f5ccfdd11bf0e', '147', '');
INSERT INTO `tp_main_role_permission` VALUES ('99759986d4164981847bc923965b0310', '609ed763ae3c487680771312e1e5b6e0', '171', '');
INSERT INTO `tp_main_role_permission` VALUES ('99ed9a2fd71f481195f66e66f214430a', 'cf4b887894544f4ebab883e55f508896', '49', '');
INSERT INTO `tp_main_role_permission` VALUES ('9ad746bf2a3f4f33a1135b8607670204', '609ed763ae3c487680771312e1e5b6e0', '13', '');
INSERT INTO `tp_main_role_permission` VALUES ('9c368b9b977a47e3bb4913001c17ae3f', 'da41ea515d4b4f05aaf635fd336ce38c', '116', '');
INSERT INTO `tp_main_role_permission` VALUES ('9caa7014d7194bee98071ef49020bbf5', '54accf8cf8904a688a5f5ccfdd11bf0e', '14', '');
INSERT INTO `tp_main_role_permission` VALUES ('9d8d67e9c212456c8669b338cc742626', '54accf8cf8904a688a5f5ccfdd11bf0e', '116', '');
INSERT INTO `tp_main_role_permission` VALUES ('9ea1623b59024c939b96c8d178c11278', '54accf8cf8904a688a5f5ccfdd11bf0e', '55', '');
INSERT INTO `tp_main_role_permission` VALUES ('a074d33506b345ce942b3e389086f5de', '54accf8cf8904a688a5f5ccfdd11bf0e', '83', '');
INSERT INTO `tp_main_role_permission` VALUES ('a0b8a6220d734cd68e58111a06bfdbc7', 'da41ea515d4b4f05aaf635fd336ce38c', '50', '');
INSERT INTO `tp_main_role_permission` VALUES ('a0bdb48fb2b44d50bf590f4787a337d5', 'da41ea515d4b4f05aaf635fd336ce38c', '134', '');
INSERT INTO `tp_main_role_permission` VALUES ('a1121ad7491940af8d8bacc4e143e5b4', 'da41ea515d4b4f05aaf635fd336ce38c', '57', '');
INSERT INTO `tp_main_role_permission` VALUES ('a3330fb306fa40d4a3b75b6b284de94b', '54accf8cf8904a688a5f5ccfdd11bf0e', '135', '');
INSERT INTO `tp_main_role_permission` VALUES ('a4ddcbb43ea844ffac33623f57f26ea4', '609ed763ae3c487680771312e1e5b6e0', '3', '');
INSERT INTO `tp_main_role_permission` VALUES ('a5d8b0aa632646d58ebe6219b66925c3', 'da41ea515d4b4f05aaf635fd336ce38c', '164', '');
INSERT INTO `tp_main_role_permission` VALUES ('a5e156a68785485c80a22844a77ad143', '54accf8cf8904a688a5f5ccfdd11bf0e', '107', '');
INSERT INTO `tp_main_role_permission` VALUES ('a703d62003894e2f938d496fc424c785', '30ef31f39d664c47b07ca800f24704ca', '1', 'parent');
INSERT INTO `tp_main_role_permission` VALUES ('a77cf77f5b2b4d7c8a6c372bb15dc40d', '54accf8cf8904a688a5f5ccfdd11bf0e', '17', '');
INSERT INTO `tp_main_role_permission` VALUES ('a825f568ac6a4ad596bd2d67b2dfb137', 'da41ea515d4b4f05aaf635fd336ce38c', '55', '');
INSERT INTO `tp_main_role_permission` VALUES ('a82dbe1fd4a44f169dec78920838ee94', '54accf8cf8904a688a5f5ccfdd11bf0e', '120', '');
INSERT INTO `tp_main_role_permission` VALUES ('a867ce7802ea433eaa69ec4edf5f161f', '54accf8cf8904a688a5f5ccfdd11bf0e', '56', '');
INSERT INTO `tp_main_role_permission` VALUES ('a8f93ef5fc7e4f32aab82e70ea508a1c', 'cf4b887894544f4ebab883e55f508896', '154', '');
INSERT INTO `tp_main_role_permission` VALUES ('a92804b63d47494b84aebddc8cdbeb15', '54accf8cf8904a688a5f5ccfdd11bf0e', '173', '');
INSERT INTO `tp_main_role_permission` VALUES ('a9c811174734422fa02e0c795db48229', '54accf8cf8904a688a5f5ccfdd11bf0e', '163', '');
INSERT INTO `tp_main_role_permission` VALUES ('aa2e000d69064c8bb9358cd8be286a0b', '54accf8cf8904a688a5f5ccfdd11bf0e', '13', '');
INSERT INTO `tp_main_role_permission` VALUES ('ab079f29739e4155968c19dfe53e48fd', '54accf8cf8904a688a5f5ccfdd11bf0e', '123', '');
INSERT INTO `tp_main_role_permission` VALUES ('abf47f27c37944db8da4d1164d815ca9', 'da41ea515d4b4f05aaf635fd336ce38c', '51', '');
INSERT INTO `tp_main_role_permission` VALUES ('ae8f3afb6ccb4c6da2d0f9c9120f440b', 'da41ea515d4b4f05aaf635fd336ce38c', '111', '');
INSERT INTO `tp_main_role_permission` VALUES ('aead08995643424fa62c454602effc02', 'da41ea515d4b4f05aaf635fd336ce38c', '112', '');
INSERT INTO `tp_main_role_permission` VALUES ('af5e64498730426e87c5540da53d468d', 'da41ea515d4b4f05aaf635fd336ce38c', '148', '');
INSERT INTO `tp_main_role_permission` VALUES ('afbb7ccad9274b1791e5c3de6aa88637', '54accf8cf8904a688a5f5ccfdd11bf0e', '9', '');
INSERT INTO `tp_main_role_permission` VALUES ('b09a0cc65dbb496bbdd187342c2820a4', '54accf8cf8904a688a5f5ccfdd11bf0e', '32', '');
INSERT INTO `tp_main_role_permission` VALUES ('b11a55d2978443e3935f04e7c2edc468', '609ed763ae3c487680771312e1e5b6e0', '23', '');
INSERT INTO `tp_main_role_permission` VALUES ('b1a3eeb0ffaf4bd4af3781a69326cc25', '54accf8cf8904a688a5f5ccfdd11bf0e', '150', '');
INSERT INTO `tp_main_role_permission` VALUES ('b416f503d2af4ed0abe23d7ff91ad765', 'da41ea515d4b4f05aaf635fd336ce38c', '46', '');
INSERT INTO `tp_main_role_permission` VALUES ('b4ce1f3151ae4fa5869394c6fc328a70', '54accf8cf8904a688a5f5ccfdd11bf0e', '36', '');
INSERT INTO `tp_main_role_permission` VALUES ('b4ebda340dcd40888fbc5aa54d7b85a7', '609ed763ae3c487680771312e1e5b6e0', '10', '');
INSERT INTO `tp_main_role_permission` VALUES ('b5a343a4bdf5481ab95c3cdf0ea0dd22', '54accf8cf8904a688a5f5ccfdd11bf0e', '75', '');
INSERT INTO `tp_main_role_permission` VALUES ('b5e6f6e1e4bb450e88ff5edd06b8591f', '609ed763ae3c487680771312e1e5b6e0', '9', '');
INSERT INTO `tp_main_role_permission` VALUES ('b735a0bf2add4125a8b880433a927787', '54accf8cf8904a688a5f5ccfdd11bf0e', '143', '');
INSERT INTO `tp_main_role_permission` VALUES ('b83d18af46e14a7e82b9929b932d16e9', 'cf4b887894544f4ebab883e55f508896', '52', '');
INSERT INTO `tp_main_role_permission` VALUES ('b9fcf830462c4ad0a37f627949fe1ea6', '609ed763ae3c487680771312e1e5b6e0', '5', '');
INSERT INTO `tp_main_role_permission` VALUES ('bb255ba48dd84489af71ea809b82bdc0', 'cf4b887894544f4ebab883e55f508896', '50', '');
INSERT INTO `tp_main_role_permission` VALUES ('bc11592a90964b16bf0f7b2dd221281e', '30ef31f39d664c47b07ca800f24704ca', '18', '');
INSERT INTO `tp_main_role_permission` VALUES ('bd45d4125e1d4e04b55e79ec710498b2', '54accf8cf8904a688a5f5ccfdd11bf0e', '158', '');
INSERT INTO `tp_main_role_permission` VALUES ('bdc5529dc1a34238b65440509e387149', '54accf8cf8904a688a5f5ccfdd11bf0e', '76', '');
INSERT INTO `tp_main_role_permission` VALUES ('bfa72f795e55414cb302807969386440', '2b40bd8babc14f9ca426a9b9f97d07ee', '1', 'parent');
INSERT INTO `tp_main_role_permission` VALUES ('c16253bf64824906a3d4b400bae521ac', '54accf8cf8904a688a5f5ccfdd11bf0e', '155', '');
INSERT INTO `tp_main_role_permission` VALUES ('c3bd4c425cdd4c288c15e08e3723a68b', 'cf4b887894544f4ebab883e55f508896', '58', '');
INSERT INTO `tp_main_role_permission` VALUES ('c3e9ed6673b04379949f340127e9364c', 'da41ea515d4b4f05aaf635fd336ce38c', '118', '');
INSERT INTO `tp_main_role_permission` VALUES ('c784422b5eb349efa3deff02e1e4cebc', '54accf8cf8904a688a5f5ccfdd11bf0e', '106', '');
INSERT INTO `tp_main_role_permission` VALUES ('c8214a3f66c845e9bc45c70457db090e', '54accf8cf8904a688a5f5ccfdd11bf0e', '166', '');
INSERT INTO `tp_main_role_permission` VALUES ('c8f544aff086409982309f87b8ba3f94', '30ef31f39d664c47b07ca800f24704ca', '13', '');
INSERT INTO `tp_main_role_permission` VALUES ('cab6e32d6932444f8048669de9f5864b', 'da41ea515d4b4f05aaf635fd336ce38c', '159', '');
INSERT INTO `tp_main_role_permission` VALUES ('cc7d6695741e47aebf754a1601da59e0', 'da41ea515d4b4f05aaf635fd336ce38c', '24', '');
INSERT INTO `tp_main_role_permission` VALUES ('cfc768b677e74c05b192bec1acbbaace', '54accf8cf8904a688a5f5ccfdd11bf0e', '154', '');
INSERT INTO `tp_main_role_permission` VALUES ('d0123332cd2746c1890ba8b14b70752d', '54accf8cf8904a688a5f5ccfdd11bf0e', '11', '');
INSERT INTO `tp_main_role_permission` VALUES ('d0621205700b472789606efe7c67729c', '54accf8cf8904a688a5f5ccfdd11bf0e', '151', '');
INSERT INTO `tp_main_role_permission` VALUES ('d1f497d780f14d7ea5821939a857f078', '54accf8cf8904a688a5f5ccfdd11bf0e', '19', '');
INSERT INTO `tp_main_role_permission` VALUES ('d270b1d2476447a48b26f91a048bad90', '30ef31f39d664c47b07ca800f24704ca', '20', '');
INSERT INTO `tp_main_role_permission` VALUES ('d3ce6e9f688b493a808ea9d47dd2b232', '54accf8cf8904a688a5f5ccfdd11bf0e', '117', '');
INSERT INTO `tp_main_role_permission` VALUES ('d4e6cf1ecb8542dfa75c0553e855945f', '54accf8cf8904a688a5f5ccfdd11bf0e', '18', '');
INSERT INTO `tp_main_role_permission` VALUES ('d5328ee953f34f058669737883441fec', '30ef31f39d664c47b07ca800f24704ca', '19', '');
INSERT INTO `tp_main_role_permission` VALUES ('d6def2eef389453f9adbdd47f47b2ffd', '54accf8cf8904a688a5f5ccfdd11bf0e', '89', '');
INSERT INTO `tp_main_role_permission` VALUES ('d7621a13d5f24eacb4c347bb8a14d4f2', '54accf8cf8904a688a5f5ccfdd11bf0e', '145', '');
INSERT INTO `tp_main_role_permission` VALUES ('d814b03f6b024eba8327714d24da2d0c', 'da41ea515d4b4f05aaf635fd336ce38c', '157', '');
INSERT INTO `tp_main_role_permission` VALUES ('d8967ec17699453dbdb2768e789a54f4', '54accf8cf8904a688a5f5ccfdd11bf0e', '110', '');
INSERT INTO `tp_main_role_permission` VALUES ('d8d21cd686c54a0989035733c70b103e', 'cf4b887894544f4ebab883e55f508896', '57', '');
INSERT INTO `tp_main_role_permission` VALUES ('da164df9fd1f419183f012ae377c3bd2', '54accf8cf8904a688a5f5ccfdd11bf0e', '28', '');
INSERT INTO `tp_main_role_permission` VALUES ('dac94d537a7846e9a671050b63da1540', 'da41ea515d4b4f05aaf635fd336ce38c', '52', '');
INSERT INTO `tp_main_role_permission` VALUES ('db8664c1faad4f91af0635b0d7601ce6', '2b40bd8babc14f9ca426a9b9f97d07ee', '2', 'parent');
INSERT INTO `tp_main_role_permission` VALUES ('dc5117ba17bb4cf8857f54d32dabf334', '54accf8cf8904a688a5f5ccfdd11bf0e', '12', '');
INSERT INTO `tp_main_role_permission` VALUES ('dc88add958c74cd78de8b3f005a99f57', '54accf8cf8904a688a5f5ccfdd11bf0e', '91', '');
INSERT INTO `tp_main_role_permission` VALUES ('de7cf9ed0d534b54b55c42d8846086c0', '54accf8cf8904a688a5f5ccfdd11bf0e', '5', '');
INSERT INTO `tp_main_role_permission` VALUES ('e05394054e024c4780299fe592334082', '54accf8cf8904a688a5f5ccfdd11bf0e', '170', '');
INSERT INTO `tp_main_role_permission` VALUES ('e0e0f8e7fc9f4f39882605f1a58ad73e', 'da41ea515d4b4f05aaf635fd336ce38c', '120', '');
INSERT INTO `tp_main_role_permission` VALUES ('e21824d011a54ac896f599596dcb9875', '54accf8cf8904a688a5f5ccfdd11bf0e', '10', '');
INSERT INTO `tp_main_role_permission` VALUES ('e2e0b84fc8944933a373b5c38c038d73', 'cf4b887894544f4ebab883e55f508896', '55', '');
INSERT INTO `tp_main_role_permission` VALUES ('e3a640fe2c974c7d9a8db6731ac6bda6', '54accf8cf8904a688a5f5ccfdd11bf0e', '21', '');
INSERT INTO `tp_main_role_permission` VALUES ('e55afca5653c4e5c9d5ff25f3520e7ce', 'da41ea515d4b4f05aaf635fd336ce38c', '135', '');
INSERT INTO `tp_main_role_permission` VALUES ('e6e9affa5800496b8047e040031ba03d', 'da41ea515d4b4f05aaf635fd336ce38c', '114', '');
INSERT INTO `tp_main_role_permission` VALUES ('e7ab99b183564afda52336a9da970e0c', '54accf8cf8904a688a5f5ccfdd11bf0e', '44', '');
INSERT INTO `tp_main_role_permission` VALUES ('e7ccf574f1f4494a9475d1079d9106f5', '54accf8cf8904a688a5f5ccfdd11bf0e', '130', '');
INSERT INTO `tp_main_role_permission` VALUES ('e832c7f4adcf4392adc115f721773ef6', 'da41ea515d4b4f05aaf635fd336ce38c', '129', '');
INSERT INTO `tp_main_role_permission` VALUES ('e8fb0c366ce248f28188b5d1d56ba004', 'da41ea515d4b4f05aaf635fd336ce38c', '169', '');
INSERT INTO `tp_main_role_permission` VALUES ('eb98c337849744838efb0bab3b34d9f5', '54accf8cf8904a688a5f5ccfdd11bf0e', '57', '');
INSERT INTO `tp_main_role_permission` VALUES ('ec65de4425e94f3e83825428d3b98b33', 'da41ea515d4b4f05aaf635fd336ce38c', '119', '');
INSERT INTO `tp_main_role_permission` VALUES ('eca26094636e419aaaca004ce6f11751', '54accf8cf8904a688a5f5ccfdd11bf0e', '136', '');
INSERT INTO `tp_main_role_permission` VALUES ('ee2324d75977401ab79995b69c905505', '54accf8cf8904a688a5f5ccfdd11bf0e', '129', '');
INSERT INTO `tp_main_role_permission` VALUES ('ee643b9277ce43dc87009a50bc46bc8b', '54accf8cf8904a688a5f5ccfdd11bf0e', '50', '');
INSERT INTO `tp_main_role_permission` VALUES ('ee8d25b5a2f4441ead24b130164c8109', '54accf8cf8904a688a5f5ccfdd11bf0e', '144', '');
INSERT INTO `tp_main_role_permission` VALUES ('f0068a059ebf4701b298d4c2a8085fc2', '54accf8cf8904a688a5f5ccfdd11bf0e', '115', '');
INSERT INTO `tp_main_role_permission` VALUES ('f05e65b8c1694dcc94226686e238f389', 'da41ea515d4b4f05aaf635fd336ce38c', '146', '');
INSERT INTO `tp_main_role_permission` VALUES ('f0bac0e8eca04c33947a0ed46d4a3658', '54accf8cf8904a688a5f5ccfdd11bf0e', '7', '');
INSERT INTO `tp_main_role_permission` VALUES ('f0d0341f5f13457b8cf511203fcd0ec0', '30ef31f39d664c47b07ca800f24704ca', '16', '');
INSERT INTO `tp_main_role_permission` VALUES ('f3148cc312594559b6fa86219eead30c', 'da41ea515d4b4f05aaf635fd336ce38c', '168', '');
INSERT INTO `tp_main_role_permission` VALUES ('f3a4ddc30e264d1ab6a837f8923aa241', '609ed763ae3c487680771312e1e5b6e0', '21', '');
INSERT INTO `tp_main_role_permission` VALUES ('f3c9dbdf3c984101a76e02b06909eabd', '54accf8cf8904a688a5f5ccfdd11bf0e', '128', '');
INSERT INTO `tp_main_role_permission` VALUES ('f3e1e0c97fd943debb63d1baebf9873b', '30ef31f39d664c47b07ca800f24704ca', '12', '');
INSERT INTO `tp_main_role_permission` VALUES ('f3e9d70bd0dd4d5c9e8d35c79a4e7485', '54accf8cf8904a688a5f5ccfdd11bf0e', '153', '');
INSERT INTO `tp_main_role_permission` VALUES ('f44071a329af4521a78365b23247511a', '54accf8cf8904a688a5f5ccfdd11bf0e', '134', '');
INSERT INTO `tp_main_role_permission` VALUES ('f4c797ac6df14d8bac27e3669bf81e7e', '54accf8cf8904a688a5f5ccfdd11bf0e', '122', '');
INSERT INTO `tp_main_role_permission` VALUES ('f56dcc5532454ea4bcd9b3d081e94b20', 'da41ea515d4b4f05aaf635fd336ce38c', '165', '');
INSERT INTO `tp_main_role_permission` VALUES ('f5b06d0a580d4630b296fe76f4045181', 'da41ea515d4b4f05aaf635fd336ce38c', '160', '');
INSERT INTO `tp_main_role_permission` VALUES ('f5dea5469b0e435aa0a74108f14b1a0e', '609ed763ae3c487680771312e1e5b6e0', '170', '');
INSERT INTO `tp_main_role_permission` VALUES ('f62d77f514ac473abe72a06132c65ca3', '609ed763ae3c487680771312e1e5b6e0', '17', '');
INSERT INTO `tp_main_role_permission` VALUES ('f644202c05cc49f78aec9bd4cae07e7b', 'da41ea515d4b4f05aaf635fd336ce38c', '117', '');
INSERT INTO `tp_main_role_permission` VALUES ('f74859cb615b448fb256d3eef2154523', 'cf4b887894544f4ebab883e55f508896', '47', '');
INSERT INTO `tp_main_role_permission` VALUES ('f7ee15d63e2f4e13ad5fda39c64a3569', '54accf8cf8904a688a5f5ccfdd11bf0e', '87', '');
INSERT INTO `tp_main_role_permission` VALUES ('f86dd17a27384c68ba28a268aba5e0f7', 'da41ea515d4b4f05aaf635fd336ce38c', '158', '');
INSERT INTO `tp_main_role_permission` VALUES ('f90ae4d5fbf548dda82a045cac4fdb09', 'cf4b887894544f4ebab883e55f508896', '144', '');
INSERT INTO `tp_main_role_permission` VALUES ('f934aecedf8a4f6db281ae6ae36aefe3', 'cf4b887894544f4ebab883e55f508896', '136', '');
INSERT INTO `tp_main_role_permission` VALUES ('fa7c7b9316a64638bdd4857777183e7b', '609ed763ae3c487680771312e1e5b6e0', '4', '');
INSERT INTO `tp_main_role_permission` VALUES ('fbc8ef9548314b17bf1ea7fc50c26cd5', '54accf8cf8904a688a5f5ccfdd11bf0e', '3', '');
INSERT INTO `tp_main_role_permission` VALUES ('fca05a06f874447aad487768ab3833a7', '54accf8cf8904a688a5f5ccfdd11bf0e', '15', '');
INSERT INTO `tp_main_role_permission` VALUES ('fce3bf1987de419489f8fbf06a48d02d', '54accf8cf8904a688a5f5ccfdd11bf0e', '88', '');
INSERT INTO `tp_main_role_permission` VALUES ('fd1de8e496504c62af2d68a92a1a6405', '54accf8cf8904a688a5f5ccfdd11bf0e', '156', '');
INSERT INTO `tp_main_role_permission` VALUES ('fd97e55150c24f3eaec9ae7da7799bc6', '54accf8cf8904a688a5f5ccfdd11bf0e', '149', '');
INSERT INTO `tp_main_role_permission` VALUES ('fe8ffae2ae5340028ad64fa0154a36fe', '54accf8cf8904a688a5f5ccfdd11bf0e', '86', '');
INSERT INTO `tp_main_role_permission` VALUES ('feb1dbaca67841c48bf8543047884e8e', '54accf8cf8904a688a5f5ccfdd11bf0e', '33', '');
INSERT INTO `tp_main_role_permission` VALUES ('ff877123e3e44863b69921255543e4d5', '609ed763ae3c487680771312e1e5b6e0', '19', '');

-- ----------------------------
-- Table structure for tp_main_system_log
-- ----------------------------
DROP TABLE IF EXISTS `tp_main_system_log`;
CREATE TABLE `tp_main_system_log` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `operator_name` varchar(32) DEFAULT '' COMMENT '操作人员',
  `operator_number` varchar(32) DEFAULT '' COMMENT '人员编号',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `modules` varchar(255) DEFAULT '0' COMMENT '所属模块',
  `operate_type` varchar(8) DEFAULT '0' COMMENT '操作类型',
  `operate_terminal` varchar(50) DEFAULT '' COMMENT '操作终端',
  `operate_detail` varchar(255) DEFAULT '' COMMENT '操作描述',
  `ip_address` int(16) DEFAULT NULL COMMENT 'ip地址',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统日志表';

-- ----------------------------
-- Records of tp_main_system_log
-- ----------------------------
INSERT INTO `tp_main_system_log` VALUES ('00321c4fb8de4b73a843c62f359d2eaf', 'times', null, '2020-06-11 10:13:31', '组织架构-单位管理', '删除', 'Chrome', '删除单位', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('01273935ea094b878dd414be9f385846', '1', null, '2020-07-02 14:33:17', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('015f7bc24d0340b1827438173995aead', '1', null, '2020-07-01 14:35:44', '运动员管理-运动员注册', '修改', 'Chrome', '修改运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('01b2f783cb5446db9c0fbbfeabb7b914', 'times', null, '2020-06-06 13:08:40', '个人中心-个人资料', '修改', 'Chrome', '修改个人资料', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('058d5a8a2a7e4cdf87b7731f2b99c3e1', '1', null, '2020-07-02 11:58:25', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('0af395fa847d477eb34239417a2414e0', '1', null, '2020-06-19 13:25:08', '运动员管理-运动员注册', '修改', 'Chrome', '修改运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('0d6d5fb4c804403abf27a43e78765e3b', '1', null, '2020-06-10 16:37:33', '运动员管理-运动员注册', '新建', 'Chrome', '新建国外运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('0f700a684e4e43c2a8efc0f81bafc168', 'admin', null, '2020-06-16 18:05:05', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('107aa13429f647f2b79b60001504e669', 'times', null, '2020-06-06 19:41:54', '数据统计-统计报表', '导出', 'Chrome', '导出统计报表子项目列表', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('12eca1c0876544d49f6866135d26b52b', '1', null, '2020-07-02 11:54:02', '运动员管理-运动员注册', '新建', 'Chrome', '新建国外运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('130e80d895f54b90bb9b9107a23d8977', 'times', null, '2020-06-19 13:33:38', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('14107a07c70c4d3eb5f7c84b308b0d16', 'admin', null, '2020-06-16 17:33:51', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('16041d7084524963a110a53b4ccf4a93', 'admin', null, '2020-06-16 17:59:34', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('1967b8bad80c46c49153686bcf12abc4', 'admin', null, '2020-06-11 16:13:50', '组织架构-人员管理', '删除', 'Chrome', '删除人员', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('19b3560ef3fb40a6b273cd4a4223c674', 'times', null, '2020-06-11 10:59:41', '运动员管理-年度运动员确认', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('1a2e67330cae4626847368a3f64e92b9', 'admin', null, '2020-06-18 17:11:37', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('1a373fb67d4649ec88e39f0695523aba', '1', null, '2020-06-10 15:40:26', '个人中心-个人资料', '修改', 'Chrome', '修改个人资料', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('1ab57bb1a12e4ab0a2c31278b1ad2e87', 'admin', null, '2020-06-16 18:06:37', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('1ac81cc4dc3b4f829b4a988d6e75b5a7', 'times', null, '2020-06-11 17:45:20', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('1c11f36ac521401a872cdc6569bbe14e', 'admin', null, '2020-06-11 16:14:22', '组织架构-单位管理', '删除', 'Chrome', '删除单位', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('1c4131951ab34d8b936789270778305a', 'times', null, '2020-06-06 18:48:31', '数据统计-统计报表', '导出', 'Chrome', '导出统计报表列表', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('1d2bcd47fe324c9690d61cd242d783ba', '1', null, '2020-07-02 11:51:52', '运动员管理-运动员注册', '新建', 'Chrome', '新建国外运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('1d4ed5d289524445b8e7dda4f2976dcc', 'times', null, '2020-06-11 10:56:03', '技术档案-运动员档案', '新建', 'Chrome', '新建运动员档案信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('1e2ee204fe1c4a79b7624f1995886095', 'admin', null, '2020-06-18 17:16:22', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('1e82fe58a2d74f1ca31ec51a5892e1de', 'times', null, '2020-06-06 19:05:36', '数据统计-统计报表', '导出', 'Chrome', '导出统计报表列表', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('1f1d9265648a4643bc1e19403f3627ba', 'times', null, '2020-06-11 16:52:23', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('21536e469b9e423b9ab5d512df347a2a', 'admin', null, '2020-06-18 16:17:09', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('21be9f6f0983434f868c3d1ac0bd32e9', 'times', null, '2020-06-11 10:12:28', '系统设置-数据字典-注册项目', '删除', 'Chrome', '删除注册项目', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('232fd831a0d24ec98dc229a49c869cc5', 'admin', null, '2020-06-16 17:58:22', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('267c2bd4aac44dd791d1a1651e050683', 'times', null, '2020-07-02 18:00:53', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('284182d52e984e57afddf324a9d3923b', 'admin', null, '2020-06-18 10:29:15', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('29ad120af2cd4d40ae8bb4568c96f32e', '1', null, '2020-07-01 14:32:44', '运动员管理-运动员注册', '新建', 'Chrome', '新建国内运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('2a6a519c6ab04d78904956b85e39d5e9', 'times', null, '2020-06-06 18:17:15', '数据统计-统计报表', '导出', 'Chrome', '导出统计报表列表', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('2b213741c8ed458eabb8219f74e58f7e', 'times', null, '2020-06-11 10:54:32', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('2b2af654eb3f4bb0b6d1df73bf8920dc', 'times', null, '2020-06-16 17:41:01', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('2cd2e2c4981f4920aaae3b6f17088476', '1', null, '2020-06-11 10:48:40', '运动员管理-运动员注册', '新建', 'Chrome', '新建国内运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('2e25874901de4e9aaef906a77b34f3ef', 'times', null, '2020-06-11 10:12:11', '系统设置-数据字典-注册项目', '新建', 'Chrome', '新建注册项目', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('31b73d6bdb5b4fe480e86fa9c6cd2e6d', '1', null, '2020-07-02 14:18:50', '运动员管理-运动员注册', '新建', 'Chrome', '新建国外运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('358767277a6842159a77e6730fcea166', 'admin', null, '2020-07-01 15:37:15', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('395f3a4e78884903a095d1b47ad7bbf2', 'admin', null, '2020-06-16 17:31:22', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('3b34733618654d69971d8db384392723', 'times', null, '2020-06-11 10:39:57', '系统设置-数据字典-代表单位', '新建', 'Chrome', '新建代表单位', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('3b75b40b87154aa4ad58f4cd8ba4a0ea', '1', null, '2020-06-11 16:37:56', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('3c62afdaca5d44bc858c56a9a6d084a7', 'times', null, '2020-06-11 10:22:00', '系统设置-数据字典-注册项目', '删除', 'Chrome', '删除注册项目', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('3ec67ed689eb4c969726b1e6c2652f65', 'admin', null, '2020-06-16 17:31:04', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('42bfeacc3e3e469680b104d1affb424e', 'admin', null, '2020-06-18 15:00:00', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('42d6e7493dfa4197acc38301d6c9bf6c', 'times', null, '2020-06-16 17:21:26', '数据统计-统计报表', '导出', 'Chrome', '导出统计报表子项目列表', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('43a8e4d4a49f4de295426f5c950fe50f', 'admin', null, '2020-06-16 14:23:33', '个人中心-个人资料', '修改', 'Chrome', '修改个人资料', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('443dc8ca6cb7463a87c9da355591ff5f', 'admin', null, '2020-06-28 13:51:50', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('45be984243524a59a4b82f4fc3ae5f1e', '1', null, '2020-07-01 14:37:40', '运动员管理-运动员注册', '修改', 'Chrome', '修改运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('46c68fef87c54aac8be29964b74314e6', '1', null, '2020-07-02 14:33:05', '协议到期运动员', '修改', 'Chrome', '修改运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('46cc452738bc40358d456d2acd556005', '1', null, '2020-07-02 14:44:42', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('4719dcfdc3b54711ad2f3c4a5e7386cd', 'times', null, '2020-06-11 10:13:06', '组织架构-人员管理', '删除', 'Chrome', '删除人员', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('47f51f21a1254ac09fff524def15deb7', '1', null, '2020-06-19 13:27:13', '运动员管理-运动员注册', '新建', 'Chrome', '新建国外运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('49310216c05d4e21a493b74953c65d56', 'admin', null, '2020-06-12 10:26:05', '组织架构-人员管理', '修改', 'Chrome', '修改人员', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('4a8c4e13a1a54efd9fbae926657e108c', '1', null, '2020-06-19 13:25:23', '运动员管理-运动员注册', '修改', 'Chrome', '修改运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('4abf35d300f44bdb95ae902903f8f848', 'times', null, '2020-07-02 11:58:58', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('500ff8a16f244d66ad4952e0146b71e6', 'admin', null, '2020-06-06 15:46:14', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('50bad2cd9ac1429f9b13d78f02e99c5e', '1', null, '2020-06-10 15:43:11', '个人中心-个人资料', '修改', 'Chrome', '修改个人资料', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('50df608d898347d09179a48ca8b0f296', 'times', null, '2020-06-11 10:21:33', '组织架构-单位管理', '删除', 'Chrome', '删除单位', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('522a3b6eb043467bb54791603a1a1df2', 'times', null, '2020-06-11 11:40:58', '系统设置-时间管理', '修改', 'Chrome', '修改时间', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('539e5671ed6144b3927348d2a9959201', '1', null, '2020-06-11 10:52:08', '运动员管理-运动员注册', '新建', 'Chrome', '新建国外运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('546b247d92d9493199ee06687e862dc6', 'times', null, '2020-06-11 10:56:27', '系统设置-时间管理', '新建', 'Chrome', '新建时间', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('550b84cd08664ade9d0a08ddd08efc6b', 'times', null, '2020-07-02 11:57:23', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('5655d48b0a584780a9f661aba342368c', '1', null, '2020-06-11 10:54:17', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('57f37e0344674c4b96a261e997d2a81d', 'admin', null, '2020-06-12 10:25:49', '组织架构-人员管理', '修改', 'Chrome', '修改人员', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('585d8f992b1a458a8e1631e7f93b95cd', 'times', null, '2020-06-11 10:36:26', '组织架构-项目权限', '分配项目', 'Chrome', '分配项目', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('5875f10af29c4cd9af6d1e20be142c2b', 'times', null, '2020-06-11 10:40:49', '系统设置-数据字典-代表单位', '新建', 'Chrome', '新建代表单位', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('5893c53670c54536ab46d9b2c6f968b2', 'admin', null, '2020-06-18 15:35:49', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('5a59a7eeabca4e4aa1255ef5a9444133', 'times', null, '2020-07-02 18:04:49', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('5b83069855ad40e286ccfd51ae551de9', 'admin', null, '2020-06-11 16:24:54', '组织架构-人员管理', '新建', 'Chrome', '新建人员', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('5bf29cc998e1482f8562066930a29a65', '1', null, '2020-06-10 16:39:36', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('5d032a69c01243a8ac3865b8d8951f3f', 'times', null, '2020-06-11 10:22:35', '系统设置-数据字典-注册项目', '修改', 'Chrome', '修改注册项目', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('60641305e37a429395ed760d5ae24fb8', '1', null, '2020-06-10 15:51:06', '运动员管理-运动员注册', '新建', 'Chrome', '新建国内运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('619d9170b20c4955ac86c80c92c3e848', 'times', null, '2020-06-11 10:40:06', '系统设置-数据字典-代表单位', '修改', 'Chrome', '修改代表单位', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('61fb1c74992b4810bca521a69979a6bd', 'times', null, '2020-06-11 10:12:21', '系统设置-数据字典-注册项目', '删除', 'Chrome', '删除注册项目', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('6558deea05d3499a826837637154631f', 'admin', null, '2020-07-07 11:49:55', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('66cfb4a2d85a4f0e83e0c9bdb6c54a03', 'admin', null, '2020-06-16 17:54:02', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('67401c3a8c4f42579a83d7d4b9dfcdb4', 'admin', null, '2020-06-16 17:36:39', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('691c00bb80e048ef8548f52025e97b9d', 'admin', null, '2020-06-16 16:37:28', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('6927f935a8a34ef8bd54c0bb3b23b317', '1', null, '2020-07-02 11:54:28', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('6a5f3230e5a14c94835847e05fca618e', 'admin', null, '2020-06-16 17:59:25', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('6b2f0917121443b8b7ff2569712f341b', '1', null, '2020-06-19 13:32:40', '运动员管理-运动员注册', '修改', 'Chrome', '修改运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('6b91980ea9c341d2b98d937c13c6bb1b', 'times', null, '2020-06-11 11:48:13', '系统设置-时间管理', '修改', 'Chrome', '修改时间', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('6bb9f596d95f41259d1ea0a10e01922b', 'admin', null, '2020-06-16 17:20:44', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('6e38f508164442ac8562bc5de280ee85', 'times', null, '2020-06-06 18:49:06', '数据统计-统计报表', '导出', 'Chrome', '导出统计报表列表', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('6e7737bc48b242c18368b3ae0bfddf7d', 'times', null, '2020-06-11 10:12:58', '组织架构-单位管理', '删除', 'Chrome', '删除单位', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('6e8bdffd2982405ebda08240a37736ca', 'admin', null, '2020-06-19 13:14:05', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('719b0a5f5cac48ee9be143292ba8c72c', 'admin', null, '2020-06-16 17:59:38', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('72c09d663bfd4ec69c3ab45d14d44fa0', '1', null, '2020-07-02 18:04:16', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('73092bcc3bd846cb88a510d7b10ae06a', 'times', null, '2020-06-11 14:26:54', '组织架构-角色管理', '删除', 'Chrome', '删除角色', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('768629358cf74ac69e7adf4d03c4323b', 'times', null, '2020-06-11 10:23:19', '组织架构-项目权限', '分配项目', 'Chrome', '分配项目', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('7788064b44ad4f17bfdf3838cb6cfea4', 'times', null, '2020-06-11 11:40:12', '系统设置-时间管理', '新建', 'Chrome', '新建时间', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('7d129e9bbd5041c282692fd0f559d6ae', '1', null, '2020-06-19 13:20:16', '运动员管理-运动员注册', '修改', 'Chrome', '修改运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('7d9f186042f44713894a581b3b149a0b', '1', null, '2020-06-19 13:21:26', '运动员管理-运动员注册', '修改', 'Chrome', '修改运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('7e030256e09843278b5c2d0704f8ca59', 'times', null, '2020-07-01 18:22:26', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('82451e8e311341898d895c208f95a141', 'times', null, '2020-06-11 10:12:03', '系统设置-数据字典-注册项目', '新建', 'Chrome', '新建注册项目', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('826ebc857a5744f49c0ca4a1e150ee3f', '1', null, '2020-06-10 15:43:22', '个人中心-个人资料', '修改', 'Chrome', '修改个人资料', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('827e4b355b6a4ce3af7107d570cb901e', 'admin', null, '2020-06-06 15:46:27', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('83b77857b6574ec4a39feb2df1d6d027', 'times', null, '2020-07-01 15:12:52', '系统设置-时间管理', '新建', 'Chrome', '新建时间', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('85c5f33e35f24e96abb7b880b5875c5e', 'times', null, '2020-06-11 10:21:53', '系统设置-数据字典-注册项目', '删除', 'Chrome', '删除注册项目', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('85d6a0986daa436f80899086f4172d3f', '1', null, '2020-06-19 13:21:13', '运动员管理-运动员注册', '修改', 'Chrome', '修改运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('8633add3aaa44cc580ffefc94c8990e3', 'times', null, '2020-06-06 18:50:32', '数据统计-统计报表', '导出', 'Chrome', '导出统计报表列表', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('876d16ef27914e23af78cfe2c73b3012', 'times', null, '2020-06-11 14:27:00', '组织架构-角色管理', '修改', 'Chrome', '修改角色', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('8868d4e5d24146f6a274e6283ddcfe6e', 'times', null, '2020-06-11 10:40:41', '系统设置-数据字典-代表单位', '修改', 'Chrome', '修改代表单位', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('88b958160e0e43ac97adf4bc3b114680', '1', null, '2020-07-02 14:31:35', '运动员管理-运动员注册', '新建', 'Chrome', '新建港澳台运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('896f5ea6316d4876b2941ca7e26839cd', 'times', null, '2020-06-06 18:28:05', '数据统计-统计报表', '导出', 'Chrome', '导出统计报表列表', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('89bf7be786b44ab0a1ee70bb195d4e54', 'times', null, '2020-06-11 14:03:12', '系统设置-时间管理', '新建', 'Chrome', '新建时间', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('8b0555e44033416a97e1293358e4ba05', '1', null, '2020-07-02 18:01:09', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('8d07f4be05394282b8631409c3943e14', '1', null, '2020-07-01 14:34:04', '运动员管理-运动员注册', '修改', 'Chrome', '修改运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('8daf16e153954b7ca1e5eddb86c525cf', 'admin', null, '2020-06-16 17:55:54', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('902276c5446f41ae9d06d956047e08ca', 'admin', null, '2020-06-18 17:14:40', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('90ce37d7f6e442bd88dd9d749eb2c6d9', 'admin', null, '2020-06-18 14:32:59', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('94509ba1079e4e479bca318f3c3ebd8a', '1', null, '2020-06-11 10:53:26', '运动员管理-运动员注册', '新建', 'Chrome', '新建港澳台运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('958da2a295d44116ac8db3f350de0ae8', 'admin', null, '2020-06-16 17:53:48', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('963358bb45d34e4283e59f69106119b1', 'admin', null, '2020-06-16 17:54:22', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('96b2667a56b74e22b90e73f23d521fa6', '1', null, '2020-06-10 16:15:52', '运动员管理-运动员注册', '新建', 'Chrome', '新建国内运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('97821dcd08c043639b74a08e7ae1a633', 'admin', null, '2020-06-18 17:27:01', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('97e99e8bcda048f1845046a47e4c5fae', '1', null, '2020-07-01 18:22:09', '运动员管理-运动员注册', '新建', 'Chrome', '新建国外运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('9b43fb488d4b415aa3693d05c095e2a2', 'times', null, '2020-06-06 18:27:07', '数据统计-统计报表', '导出', 'Chrome', '导出统计报表列表', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('9c0135d1f2424b8e96b57dfbd06085f9', 'times', null, '2020-06-11 11:41:31', '系统设置-时间管理', '修改', 'Chrome', '修改时间', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('9e7037b2b4814ff38c34bda277abeaaa', 'admin', null, '2020-06-16 17:55:21', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('9ffa8c241a1046fd85d2a131d346c96b', 'times', null, '2020-06-11 10:55:12', '技术档案-体测信息', '新建', 'Chrome', '新建体测信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('a4be56d362db4cb5a28bb47bd451025e', 'times', null, '2020-07-01 15:12:41', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('a5be95ca87694b0baa2334fb05816e69', 'times', null, '2020-06-11 11:47:03', '系统设置-时间管理', '修改', 'Chrome', '修改时间', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('a9c6fa2f420b4b2da3f1a78c9f175169', 'times', null, '2020-06-19 13:27:34', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('aa3338ea844946cba45a173aa96da1f7', 'admin', null, '2020-06-16 18:20:24', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('ac7aafcd722944be84440b71d47c5ac1', 'times', null, '2020-06-11 13:52:18', '系统设置-时间管理', '新建', 'Chrome', '新建时间', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('ae50bf5ce35f44bbb70c955872ff94a0', 'admin', null, '2020-06-16 18:06:43', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('b1f58a2db17149c2aa6af078da164ee3', 'times', null, '2020-07-07 11:42:42', '个人中心-个人资料', '修改', 'Chrome', '修改个人资料', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('b3406f4f1c504d50b5ff47ae254ceb88', 'admin', null, '2020-06-11 16:22:20', '组织架构-角色管理', '新建', 'Chrome', '新建角色', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('b355958a35bd4dc69093fa2d1cc8a59d', 'times', null, '2020-07-02 11:58:43', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('b3c704898a71497298c0b2d023ec1171', 'admin', null, '2020-06-16 09:42:39', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('b7fcb297d5d34185a7788f933081c9b3', '1', null, '2020-06-19 13:25:17', '运动员管理-运动员注册', '修改', 'Chrome', '修改运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('b94d23712b51470788e79fe7056d8dd6', 'times', null, '2020-06-06 18:19:27', '数据统计-统计报表', '导出', 'Chrome', '导出统计报表列表', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('bb696c5a2bcf4af198c467999d317283', '1', null, '2020-07-01 15:09:11', '运动员管理-运动员注册', '新建', 'Chrome', '新建国内运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('bb7d832ce4f04dfa9cb43cf55b709bc9', 'admin', null, '2020-06-11 14:00:10', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('bdde14ba6ebe4554866eb1e3ce5e322a', 'admin', null, '2020-06-18 17:13:44', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('c15e8103cd0549e287b8eb217c8f5785', 'times', null, '2020-06-11 10:40:17', '系统设置-数据字典-代表单位', '新建', 'Chrome', '新建代表单位', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('c327f46a409f4638bc57612638720bac', 'times', null, '2020-06-11 10:22:44', '系统设置-数据字典-注册项目', '新建', 'Chrome', '新建注册项目', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('c49bd1ddc44341e4af697e1cb4d41793', 'admin', null, '2020-06-16 18:00:26', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('ca0023835e8b43adaa17ba6c090f7354', 'admin', null, '2020-06-16 17:20:21', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('ca4cf4f0d4044fcbacd4b03452465446', 'times', null, '2020-06-06 19:06:42', '数据统计-统计报表', '导出', 'Chrome', '导出统计报表列表', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('cacda8c66ece4e6e914d4c7d7fc13cfa', '1', null, '2020-06-19 13:19:47', '运动员管理-运动员注册', '修改', 'Chrome', '修改运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('cb496f1b3ba5459ead2502e0d6fce47c', 'times', null, '2020-06-06 18:45:42', '数据统计-统计报表', '导出', 'Chrome', '导出统计报表列表', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('cd13e950e10e4605aa562761234699a7', 'times', null, '2020-07-02 11:54:44', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('d394416bb97346019d1a091f79da270e', 'times', null, '2020-06-11 13:51:15', '系统设置-时间管理', '新建', 'Chrome', '新建时间', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('d39de5727d9a4e66b8b05c5d33874c47', 'admin', null, '2020-06-16 11:01:51', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('d42c677d27924d318f101e7e48e7ea00', 'admin', null, '2020-06-16 17:41:39', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('d74a7b7db8f3493e96d0edc476346467', 'times', null, '2020-06-10 16:43:27', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('d7504db8a1ab4f64b4d129d947bec5a8', 'times', null, '2020-06-11 13:44:30', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('d90387b7768047a8bc392100aeddb037', 'times', null, '2020-07-03 09:35:35', '技术档案-运动员档案', '新建', 'Chrome', '新建运动员档案信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('da83fe457ab24294be6b87e1a2c12b51', 'times', null, '2020-06-11 10:36:20', '组织架构-项目权限', '分配项目', 'Chrome', '分配项目', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('dbd9f7902bb145c9ba05e2410dfcfb43', '1', null, '2020-06-10 16:16:00', '运动员管理-运动员注册', '新建', 'Chrome', '新建国内运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('dd9f2cd7bef04574b3670e9774e8642c', '1', null, '2020-06-19 13:33:25', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('e1c47ca2872e437087b00b37454ee57e', 'times', null, '2020-06-16 17:40:11', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('e28964272a5b46e1a1bed6f8cf7c6e7c', 'admin', null, '2020-06-16 16:35:41', '组织架构-人员管理', '新建', 'Chrome', '新建人员', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('e3af6de3f13341028c3829b3f52a81cd', '1', null, '2020-07-02 18:00:03', '运动员管理-运动员注册', '新建', 'Chrome', '新建国外运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('e3e409c5135d4df38565e7abee3121d7', 'admin', null, '2020-06-18 14:37:56', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('e4b845f2d5e34eaf9eda3d545a5d206d', '1', null, '2020-07-01 15:09:15', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('e6336844ff4a4da9881c8cbdda94d60d', 'admin', null, '2020-06-16 18:04:38', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('ed02b50af70e41d3a82ba9da44146bc0', 'admin', null, '2020-06-18 17:26:20', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('ed0eb969c34a4dc5b469235d27d51f3c', 'times', null, '2020-06-18 10:21:55', '消息公告-系统消息', '撤回', 'Chrome', '撤回系统消息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('edb173c6b06642129bb8f74dafbaf66c', '1', null, '2020-06-10 16:39:30', '运动员管理-运动员注册', '新建', 'Chrome', '新建港澳台运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('edd69a75c2a040ebac2d9c6b1131e12d', 'admin', null, '2020-06-06 19:41:40', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('ee14a43e5541499f9f499a22c39d13e0', 'times', null, '2020-06-11 14:27:10', '组织架构-角色管理', '修改', 'Chrome', '修改角色', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('ef980d96020e4df78077cda315d3c79f', 'admin', null, '2020-06-06 17:03:13', '组织架构-角色管理', '权限设置', 'Chrome', '权限设置', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('efdc533b656e4934bd6389b50f8b1c68', 'times', null, '2020-07-02 18:04:43', '运动员管理-运动员审核', '通过审核', 'Chrome', '通过审核', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('f012653df6424846a18ea213fd9c7b6b', '1', null, '2020-06-10 16:30:40', '运动员管理-运动员注册', '新建', 'Chrome', '新建国内运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('f1dcb19b388c48bfbd595d66e75f4be1', '1', null, '2020-07-01 18:22:15', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('f2a61b36844e465b8ffeb6a007b83c5f', '1', null, '2020-06-19 13:27:18', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('f5e8442bd70945c4805f5b41be5903bc', 'times', null, '2020-06-11 10:12:31', '系统设置-数据字典-注册项目', '删除', 'Chrome', '删除注册项目', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('f8b39f41a6c14208ae814f781d274af1', '1', null, '2020-07-02 14:36:52', '运动员管理-运动员上报', '上报', 'Chrome', '上报运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('fe5a2125bb324e05abb7cbd2d686af0a', '1', null, '2020-06-19 13:19:39', '运动员管理-运动员注册', '新建', 'Chrome', '新建国内运动员信息', '-1062731400', null);
INSERT INTO `tp_main_system_log` VALUES ('ff79e863338b4a6c9c8fa06474466466', 'times', null, '2020-06-11 10:15:58', '系统设置-数据字典-注册项目', '删除', 'Chrome', '删除注册项目', '-1062731400', null);
