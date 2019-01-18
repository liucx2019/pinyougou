  	//品牌服务层
    	app.service('brandService',function($http){
    		//读取列表数据绑定到表单中
    		this.findAll=function(){
    			return $http.get('/brand/findAll.do');
    		}
    		//分页 
    		this.findPage=function(page,size){
    			return $http.get('/brand/findPage.do?page='+page+'&size='+size);
    		}
    		//添加
    		this.add=function(entity){
    			return $http.post('/brand/add.do',entity);
    		}
    		//更新数据
    		this.update=function(entity){
    			return  $http.post('/brand/update.do',entity);
    		}
    		
    		//查询实体
    		this.findOne=function(id){
    			return $http.get('/brand/findOne.do?id='+id);
    		}
    		
    		//批量删除
    		this.dele=function(id){
    			return $http.get("/brand/delete.do?ids="+id);
    		}
    		
    		//模糊查询
    		this.search=function(page,size,searchEntity){
    			return $http.post('/brand/search.do?page='+page+'&size='+size,searchEntity);
    		}
    		
    		
    		//下拉列表数据
        	this.selectOptionList=function(){
        		return $http.get('../brand/selectOptionList.do');
        	}
    		
    	});
    	
    	
    	