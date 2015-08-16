<#compress>
<#if pagination??>
<ul class="pagination pagination-sm m-t-sm m-b-none" id="pagination">
    <#if pagination.totalPage gt 0>
        <!-- 上一页 -->
        <#if pagination.pageNumber == 1>
            <li>
                <a href="javascript:void(0);">
                    <i class="fa fa-chevron-left"></i>
                </a>
            </li>
        <#else>
            <#if pagination.totalPage == 1>
                <li>
                    <a href="javascript:void(0);">
                        <i class="fa fa-chevron-left"></i>
                    </a>
                </li>
            <#else>
                <li>
                    <a href="javascript:void(0);" data-pagenumber="${pagination.pageNumber - 1}">
                        <i class="fa fa-chevron-left"></i>
                    </a>
                </li>
            </#if>
        </#if>

        <!-- 分页 -->
        <#assign showNumbers=10 />
        <#if pagination.totalPage gt showNumbers>
            <#assign halfSize=showNumbers/2 />
            <#if pagination.pageNumber-halfSize < 1>
                <#assign startIndex=1 />
                <#assign endIndex=showNumbers />
            <#elseif pagination.totalPage - pagination.pageNumber lt halfSize>
                <#assign startIndex=pagination.totalPage - showNumbers + 1 />
                <#assign endIndex=pagination.totalPage/>
            <#else>
                <#assign startIndex=pagination.pageNumber - halfSize />
                <#assign endIndex=pagination.pageNumber + halfSize - 1 />
            </#if>
        <#else>
            <#assign startIndex=1 />
            <#assign endIndex=pagination.totalPage />
        </#if>

        <#list startIndex..endIndex as i>
            <#if i == pagination.pageNumber>
                <li class="active">
                    <a href="javascript:void(0);">${i}</a>
                </li>
            <#else>
                <li>
                    <a href="javascript:void(0);" data-pagenumber="${i}">${i}</a>
                </li>
            </#if>
        </#list>

        <!-- 下一页 -->
        <#if pagination.pageNumber == pagination.totalPage>
            <li>
                <a href="javascript:void(0);">
                    <i class="fa fa-chevron-right"></i>
                </a>
            </li>
        <#else>
            <#if pagination.totalPage == 1>
                <li>
                    <a href="javascript:void(0);">
                        <i class="fa fa-chevron-right"></i>
                    </a>
                </li>
            <#else>
                <li>
                    <a href="javascript:void(0);" data-pagenumber="${pagination.pageNumber + 1}">
                        <i class="fa fa-chevron-right"></i>
                    </a>
                </li>
            </#if>
        </#if>
    <#else>
        <li>
            <a href="#">
                <i class="fa fa-chevron-left"></i>
            </a>
        </li>
        <li>
            <a href="#">
                <i class="fa fa-chevron-right"></i>
            </a>
        </li>
    </#if>
</ul>
</#if>
</#compress>