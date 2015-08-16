package app.kits;

import com.github.sog.libs.MimeTypes;
import com.github.sog.plugin.monogodb.MorphiaKit;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * .
 * </p>
 *
 * @author sagyf yang
 * @version 1.0 2014-04-15 14:59
 * @since JDK 1.6
 */
public class AppKit {

    private static final Logger logger = LoggerFactory.getLogger(AppKit.class);

    public static final Splitter RN_SPLITTER = Splitter.on("\n").omitEmptyStrings().trimResults();

    public static String saveToGridFs(File file) {
        String storage_file_name = EncodeKit.toUUID();
        try {
            MorphiaKit.getDataStore();
            GridFS myFS = GridFsKit.getGridFS();
            GridFSInputFile gfsFile = myFS.createFile(file);

            gfsFile.setFilename(storage_file_name);
            gfsFile.setContentType(MimeTypes.getContentType(file.getName()));
            gfsFile.save();
        } catch (IOException e) {
            logger.error("The Atds upload file is error!", e);
            return null;
        } finally {
            FileUtils.deleteQuietly(file);
        }
        return storage_file_name;
    }

    /**
     * 按换行符号来分割字符并组成一个集合返回.
     *
     * @param char_str 分割的字符串
     * @return 集合返回
     */
    public static List<String> splitAndTrimChar(String char_str) {
        List<String> result = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(char_str)) {
            final Set<String> temp = Sets.newHashSet(RN_SPLITTER.split(char_str));
            for (String s : temp) {
                if (!Strings.isNullOrEmpty(s)) {
                    result.add(s.replace("\r", ""));
                }
            }
        }
        return result;
    }


    public static int age(Date brithday) {
        DateTime now = DateTime.now();
        return now.getYear() - new DateTime(brithday).getYear();
    }


    public static void wirteFileToPathWithGridFs(String filename, String path) {
        GridFSDBFile imageForOutput = GridFsKit.getFileWithName(filename);
        try {
            imageForOutput.writeTo(new File(path));
        } catch (IOException e) {
            logger.error("read gridfs file is error!", e);
        }
    }

     /**
     * 判断给定字符串是否身份证
     *
     * @param idCard 身份证字符串
     * @return 是否是身份证
     */
    public static boolean isIdCard(String idCard) {
        try {
            return IDCardValidate.validate(idCard);
        } catch (Exception e) {
            logger.error("check id_card error!", e);
        }
        return false;
    }

    /**
     * 功能：判断字符串是否为数字
     * @param str 需要验证的字符传
     * @return 是否是数字
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 验证日期字符串是否是YYYY-MM-DD格式
     * @param str 需要验证的字符串
     * @return 是否日期格式
     */
    public static boolean isDataFormat(String str){
        String re="^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1=Pattern.compile(re);
        Matcher isNo=pattern1.matcher(str);
        return isNo.matches();
    }

    private static class IDCardValidate {
        private static String[] valCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2" };
        private static String[] wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2" };
        private static String ai = "";

        private static boolean validate(String idCard) throws ParseException {
            if (idCard == null) {
                return false;
            }

            // ================ 号码的长度 15位或18位 ================
            if (idCard.length() != 15 && idCard.length() != 18) {
                //"身份证号码长度应该为15位或18位。";
                return false;
            }

            // ================ 数字 除最后以为都为数字 ================
            if (idCard.length() == 18) {
                ai = idCard.substring(0, 17);
            } else if (idCard.length() == 15) {
                ai = idCard.substring(0, 6) + "19" + idCard.substring(6, 15);
            }
            if (!isNumber(ai)) {
                //"身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
                return false;
            }

            // ================ 出生年月是否有效 ================
            String strYear = ai.substring(6, 10);// 年份
            String strMonth = ai.substring(10, 12);// 月份
            String strDay = ai.substring(12, 14);// 月份
            if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
                //"身份证生日无效。";
                return false;
            }
            GregorianCalendar gc = new GregorianCalendar();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                //errorInfo = "身份证生日不在有效范围。";
                return false;
            }
            if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
                //errorInfo = "身份证月份无效";
                return false;
            }
            if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
                //errorInfo = "身份证日期无效";
                return false;
            }

            // ================ 地区码时候有效 ================
            Hashtable h = getAreaCode();
            if (h.get(ai.substring(0, 2)) == null) {
                //errorInfo = "身份证地区编码错误。";
                return false;
            }

            // ================ 判断最后一位的值 ================
            int totalmulAiWi = 0;
            for (int i = 0; i < 17; i++) {
                totalmulAiWi = totalmulAiWi
                        + Integer.parseInt(String.valueOf(ai.charAt(i)))
                        * Integer.parseInt(wi[i]);
            }
            int modValue = totalmulAiWi % 11;
            String strVerifyCode = valCodeArr[modValue];
            ai = ai + strVerifyCode;

            if (idCard.length() == 18) {
                if (ai.equals(idCard) == false) {
                    return false;
                }
            }

            return true;
        }

        /**
         * 功能：设置地区编码
         * @return Hashtable 对象
         */
        private static Hashtable getAreaCode() {
            Hashtable hashtable = new Hashtable();
            hashtable.put("11", "北京");
            hashtable.put("12", "天津");
            hashtable.put("13", "河北");
            hashtable.put("14", "山西");
            hashtable.put("15", "内蒙古");
            hashtable.put("21", "辽宁");
            hashtable.put("22", "吉林");
            hashtable.put("23", "黑龙江");
            hashtable.put("31", "上海");
            hashtable.put("32", "江苏");
            hashtable.put("33", "浙江");
            hashtable.put("34", "安徽");
            hashtable.put("35", "福建");
            hashtable.put("36", "江西");
            hashtable.put("37", "山东");
            hashtable.put("41", "河南");
            hashtable.put("42", "湖北");
            hashtable.put("43", "湖南");
            hashtable.put("44", "广东");
            hashtable.put("45", "广西");
            hashtable.put("46", "海南");
            hashtable.put("50", "重庆");
            hashtable.put("51", "四川");
            hashtable.put("52", "贵州");
            hashtable.put("53", "云南");
            hashtable.put("54", "西藏");
            hashtable.put("61", "陕西");
            hashtable.put("62", "甘肃");
            hashtable.put("63", "青海");
            hashtable.put("64", "宁夏");
            hashtable.put("65", "新疆");
            hashtable.put("71", "台湾");
            hashtable.put("81", "香港");
            hashtable.put("82", "澳门");
            hashtable.put("91", "国外");
            return hashtable;
        }
    }

}
