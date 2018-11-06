$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'admin/systitle/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true },
			{ label: '文章标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '文章图片', name: 'imageurl', index: 'imageUrl', width: 80 }, 			
			{ label: '图片的宽', name: 'imagewidth', index: 'imageWidth', width: 80 }, 			
			{ label: '图片的高', name: 'imageheight', index: 'imageHeight', width: 80 }, 			
			{ label: '是否原创 默认1原创', name: 'original', index: 'original', width: 80 }, 			
			{ label: '', name: 'type', index: 'type', width: 80 }, 			
			{ label: '内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '是否私密，默认0不私密', name: 'privatestatus', index: 'privateStatus', width: 80 }, 			
			{ label: '默认0，不删除', name: 'delstatus', index: 'delStatus', width: 80 }, 			
			{ label: '默认0 需审核', name: 'adminstatus', index: 'adminStatus', width: 80 }, 			
			{ label: '对应sys_zone的ID', name: 'zoneid', index: 'zoneID', width: 80 }, 			
			{ label: '作者', name: 'author', index: 'author', width: 80 }, 			
			{ label: '标签', name: 'label', index: 'label', width: 80 }, 			
			{ label: '喜欢', name: 'likecount', index: 'likeCount', width: 80 }, 			
			{ label: '不喜欢', name: 'notlikecount', index: 'notLikeCount', width: 80 }, 			
			{ label: '日期', name: 'createdate', index: 'createDate', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		sysTitle: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sysTitle = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.sysTitle.id == null ? "admin/systitle/save" : "admin/systitle/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.sysTitle),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "admin/systitle/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "admin/systitle/info/"+id, function(r){
                vm.sysTitle = r.sysTitle;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});