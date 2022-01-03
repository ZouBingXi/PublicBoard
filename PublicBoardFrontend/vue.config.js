module.exports = {
	outputDir: '../src/main/resources/static',
	devServer:{
		proxy: {
			'/apis':{
				target: 'http://localhost/',  // 后台api
				changeOrigin: true,  //是否跨域
				// secure: true,
				pathRewrite: {
					'^/apis': ''   //需要rewrite的,
				}
			}
		},
		port:9090,
		open:true
	}
}

