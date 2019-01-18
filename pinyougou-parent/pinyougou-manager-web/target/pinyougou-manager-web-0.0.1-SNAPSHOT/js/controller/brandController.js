



		//品牌控制层
    	app.controller('brandController',function($scope,$controller,brandService){
    		
    		$controller('baseController',{$scope:$scope});//继承
    		//读取列表数据绑定到表单中
    		$scope.findAll = function(){
    			brandService.findAll().success(function(response){
    				$scope.list =response;
    			});
    				
    		}
    		
    		
    		//分页控件配置currentPage:当前页   totalItems :总记录数  itemsPerPage:每页记录数  perPageOptions :分页选项  onChange:当页码变更后自动触发的方法
        	$scope.paginationConf={
        			currentPage: 1,
    				totalItems: 10,
    				itemsPerPage: 10,
    				perPageOptions: [10, 20, 30, 40, 50],
    				onChange:function(){
    					$scope.reloadList();//重新加载
    				}
        	};
        	
        	//刷新列表
        	$scope.reloadList=function(){
        		//切换页码
        		$scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage)
        	}
        	
        	//分页 
        	$scope.findPage=function(page,size){
        		brandService.findPage(page,size).success(function(response){
        			$scope.list=response.rows;//显示当前页数据 
        			$scope.paginationConf.totalItems=response.total;//更新总记录数 
        		});
        	}
    		
        	//保存
    		$scope.save=function(){
        		
        		var object;//方法名称
        		
        		if($scope.entity.id!=null){//如果有 ID
        			object=brandService.update($scope.entity);//则执行修改方法
        		}else{
        			object=brandService.add($scope.entity);
        		}
        		
        			object.success(function(response){
        			if(response.success){
        				$scope.reloadList();//刷新
        			}else{
        				alert(response.message);
        			}
        		});
        	}
    		
    		//查询实体
    		$scope.findOne=function(id){
    			brandService.findOne(id).success(function(response){
    				$scope.entity=response;
    			});
    		}
    		
    		
    	/*	$scope.selectId =[]; //用户勾选的ID集合 
    		//用户勾选复选框 
    		$scope.updateSelection=function(id,$event){
    			if($event.target.checked){
    				$scope.selectId.push(id);//push向集合添加元素 
    			}else{
    				var index =$scope.selectId.indexOf(id);//查找值的 位置
    				$scope.selectId.splice(index,1);//参数1：移除的位置 参数2：移除的个数
    			}
    		}*/
    	
    		//批量删除
    		$scope.dele=function(){
    			if(confirm("您确认删除么?")){
    				brandService.dele($scope.selectIds).success(function(response){
    					if(response.success){
    						$scope.reloadList();//刷新
    					}else{
    						alert(response.message);
    					}
    				});
    			}
    		}
    		
    		
    		
    		//定义搜索为空
    		$scope.searchEntity={}; 
    		//搜索
    		$scope.search=function(page,size){
    			brandService.search(page,size,$scope.searchEntity).success(function(response){
    				$scope.list=response.rows;//给列表变量赋值
    				$scope.paginationConf.totalItems=response.total;//更新总记录数 
    			});
    		}
    		
    		
    	});
    	