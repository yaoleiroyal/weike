/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package app.models;

import com.github.sog.config.StringPool;
import com.github.sog.controller.datatables.core.ColumnDef;
import com.github.sog.controller.datatables.core.DatatablesCriterias;
import com.github.sog.libs.AppFunc;
import com.github.sog.plugin.sqlinxml.SqlKit;
import com.google.common.base.Strings;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>
 * .
 * </p>
 *
 * @author sagyf yang
 * @version 1.0 2014-04-10 2:05
 * @since JDK 1.6
 */
public class DaoKit {

    private static final Logger logger = LoggerFactory.getLogger(DaoKit.class);


    public static String like(String value) {
        return StringPool.PERCENT + Strings.nullToEmpty(value) + StringPool.PERCENT;
    }

    public static <M extends Model> boolean isNew(M m) {
        return m.getNumber(AppFunc.TABLE_PK_COLUMN) == null;
    }


    public static boolean batch_update(String sql, List<? extends Model> params) {
        checkNotNull(sql);
        checkNotNull(params);
        try {
            final int size = params.size();
            Object[][] _params = new Object[size][];
            Object[] _param;

            for (int i = 0; i < size; i++) {
                Model model = params.get(i);
                final String[] attrNames = model.getAttrNames();

                _param = new Object[attrNames.length];
                for (int j = 0; j < attrNames.length; j++) {
                    String attrName = attrNames[j];
                    _param[j] = model.get(attrName);
                }
                _params[i] = _param;
            }

            Db.batch(sql, _params, 50);
            return true;
        } catch (Exception e) {
            logger.error("run batch remove is error! the sql is {{}}", sql, e);
            return false;
        }
    }

    public static boolean batch_remove(String sql, List<String> idlist) {
        checkNotNull(sql);
        checkNotNull(idlist);
        try {
            final int size = idlist.size();
            Object[][] _params = new Object[size][];
            Object[] _param;

            for (int i = 0; i < size; i++) {
                String id = idlist.get(i);
                _param = new Object[1];
                _param[0] = id;
                _params[i] = _param;
            }

            Db.batch(sql, _params, 50);
            return true;
        } catch (Exception e) {
            logger.error("run batch remove is error! the sql is {}", sql, e);
            return false;
        }
    }

    /**
     * @param sql    sql语句
     * @param idlist id
     * @return boolean
     */
    public static boolean batch_update_by_id(String sql, List<String> idlist) {
        return batch_remove(sql, idlist);
    }


    /**
     * 分页检索，默认按照id进行排序，需要指定datatables的请求参数。
     *
     * @param model_name sql conf 中的 sqlGroup 的name
     * @param criterias  请求参数
     * @return 分页数据
     */
    public static Page<Record> paginate(String model_name, DatatablesCriterias criterias) {
        return paginate(model_name, criterias, null);
    }

    /**
     * 分页检索，默认按照id进行排序，需要指定datatables的请求参数。
     *
     * @param model_name sql conf 中的 sqlGroup 的name
     * @param criterias  请求参数
     * @return 分页数据
     */
    public static Page<Record> paginate(String model_name, DatatablesCriterias criterias, List<Object> params) {
        return paginate(SqlKit.sql(model_name + ".where"), SqlKit.sql(model_name + ".columns"), criterias, SqlKit.sql(model_name + ".orders"), params);
    }


    /**
     * 分页检索，默认按照id进行排序，需要指定datatables的请求参数。
     *
     * @param where         FROM WHERE 语句.
     * @param sql_columns   SELECT column sql 语句
     * @param criterias     请求参数
     * @param default_order 默认的排序字段，类似：ORDER BY id DESC
     * @return 分页数据
     */
    public static Page<Record> paginate(String where, String sql_columns, DatatablesCriterias criterias, String default_order, List<Object> params) {
        where = Strings.nullToEmpty(where);
        int pageSize = criterias.getDisplaySize();
        int start = criterias.getDisplayStart() / pageSize + 1;
        final List<ColumnDef> sortingColumnDefs = criterias.getSortingColumnDefs();
        if (sortingColumnDefs != null && !sortingColumnDefs.isEmpty()) {
            StringBuilder orderBy = new StringBuilder();
            for (ColumnDef sortingColumnDef : sortingColumnDefs)
                if (sortingColumnDef.isSortable()) {
                    orderBy.append(sortingColumnDef.getName()).append(StringPool.SPACE).append(sortingColumnDef.getSortDirection().toString());
                }
            final String byColumns = orderBy.toString();
            if (!Strings.isNullOrEmpty(byColumns)) {
                where += " ORDER BY " + byColumns;
            }
        }
        if (!where.contains("ORDER")) {
            where += (StringPool.SPACE + default_order);
        }
        if (params == null || params.size() == 0) {
            return Db.paginate(start, pageSize, sql_columns, where);
        } else {

            return Db.paginate(start, pageSize, sql_columns, where, params.toArray());
        }
    }
}
