/**
 * 所有模块接口列表
 */
import axios from '../request/http' // 导入http中创建的axios实例

//设置接口地址
// axios.defaults.baseURL = process.env.VUE_APP_BASEURL

//系统模快
const systemModule = {
    //登录
    login (params) {
        return axios.post("login", params)
    },
    //用户管理
    usersManage: {
        //列表新增
        addUser (params) {
            return axios.post("/api/user/add", params)
        },
        //更新信息
        edit (params) {
            return axios.post("/api/user/update", params)
        },
        //列表、查询
        getList (params) {
            return axios.post("/api/user/findByPage", params)
        },
        //通过id获取数据
        getDataById (params) {
            return axios.get("/api/user/find?id="+params)
        },
        //删除
        delete (params) {
            return axios.post("/api/user/delete",params)}
            
        //分组查询下拉数据
        ,groupUserList () {
            return axios.post("/api/user/findUserList")
        },

    },//nas管理
    nasManage: {
        //列表新增
        addNas (params) {
            return axios.post("/api/nas/add", params)
        },
        //更新信息
        edit (params) {
            return axios.post("/api/nas/update", params)
        },
        //列表、查询
        getList (params) {
            return axios.post("/api/nas/findByPage", params)
        },
        //通过id获取数据
        getDataById (params) {
            return axios.get("/api/nas/find?id="+params)
        },
        //删除
        delete (params) {
            return axios.post("/api/nas/delete",params)
        },
        querySendEmailFlowBySoeid(params){
            return axios.get("/api/nas/sendemailflow?soeId="+params)
        }
    },
	//上传图片、文件pdf、word、excel（如果一个接口能支持）
    uploadFile(params, config) {
        return axios.post('uploadFile', params, config)
    },
    //上传（excel）
    batchImport(params, config) {
        return axios.post('batchImport', params, config)
    },
	//获取下拉仓库
	getStorehouseName(params) {
		return axios.post("getStorehouseName", params)
	}
}
//导出接口
export default {
    systemModule
}
