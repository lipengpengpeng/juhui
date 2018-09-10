/***********************   SAIYO文件处理辅助  **************************/

/*通过文件路径得到一个Image对象，以得到文件大小         
  Exm:if(getImageToGetSize(path).fileSize>1024000) */
function sy_GetImageToGetSize(path) {
	var image = new Image();
	image.dynsrc = path;
	return image;
}
/*通过文件路径得到一个File对象，以得到文件大小         
  Exm:if(sy_GetFileToGetSize(path).fileSize>1024000) */
function sy_GetFileToGetSize(path) {
	var file = new File();
	file.dynsrc = path;
	return file;
}  
  
//得到文件扩展名(如: jpg rar)
function sy_GetFileExt(path) {
	return path.substring(path.lastIndexOf(".") + 1);
}
/*过滤文件类型
  Exm:if(!filterFile(path,"jpg,gif,bmp,png,jpeg")*/
function sy_FilterFile(path, fileType) {
	var strExt = fileType;
	var fileExt = sy_GetFileExt(path);
	var arrExt = new Array();
	arrExt = strExt.split(",");
	for (i = 0; i < arrExt.length; i++) {
		if (fileExt.toLowerCase() == arrExt[i].toLowerCase()) {
			return true;
		}
	}
	return false;
}

