/**
 * 弹出选择框
 * author：zxk
 */

function popupbox(config) {
	this.config = {
		id : config.id,
		title : config.title,
		data : config.data,
		click : config.click
	};
	var pop_bottom_div;
	if (document.getElementById(config.id) == null) {
		pop_bottom_div = document.createElement("DIV");
		pop_bottom_div.id = this.config.id;
		pop_bottom_div.setAttribute("class", "pop_bottom_div");
	} else {
		pop_bottom_div = document.getElementById(config.id);
		pop_bottom_div.innerHTML = "";
	}

	var pop_in_div = document.createElement("DIV");
	pop_in_div.id = this.config.id + "_pop_in_div";
	pop_in_div.setAttribute("class", "pop_in_div");

	pop_bottom_div.appendChild(pop_in_div);

	if (navigator.userAgent.toLowerCase().match(/android 2.3/i) == "android 2.3") {
		if (this.config.data.length <= 10) {
			pop_in_div.style.height = "80%";
			pop_bottom_div.style.height = "100%";
		} else {
			pop_in_div.style.height = (this.config.data.length + 1) * 40 + "px";
			pop_bottom_div.style.height = (this.config.data.length + 3) * 40
					+ "px";
		}
	} else {
		pop_in_div.style.height = "80%";
		pop_in_div.style.maxHeight = "80%";
	}

	var pop_in_title_div = document.createElement("DIV");
	pop_in_title_div.id = this.config.id + "_pop_in_title_div";
	pop_in_title_div.style.width = "95%";
	pop_in_title_div.style.height = "40px";
	pop_in_title_div.style.position = "absolute";
	pop_in_title_div.style.backgroundColor = "#5ac8fa";
	pop_in_title_div.style.color = "#fff";
	pop_in_title_div.style.lineHeight = "40px";
	pop_in_title_div.style.paddingLeft = "5%";
	pop_in_title_div.innerHTML = this.config.title;

	var pop_in_content_section = document.createElement("section");
	pop_in_content_section.id = this.config.id + "_pop_in_content_section";
	pop_in_content_section.setAttribute("class", "pop_in_content_section");

	var pop_in_wc_content_div = document.createElement("DIV");
	pop_in_wc_content_div.id = this.config.id + "_pop_in_wc_content_div";
	pop_in_wc_content_div.style.width = "100%";
	pop_in_wc_content_div.style.height = "100%"
	pop_in_wc_content_div.style.background = "#fff";
	pop_in_wc_content_div.style.position = "absolute";

	var pop_in_content_div = document.createElement("ul");
	pop_in_content_div.id = this.config.id + "_pop_in_content_div";
	pop_in_content_div.style.width = "100%";
	pop_in_content_div.style.height = "100%";
	pop_in_content_div.style.background = "#fff";
	pop_in_content_div.style.overflowY = "auto";
	pop_in_content_div.style.position = "absolute";
	pop_in_content_div.style.WebkitOverflowScrolling = "touch";


	pop_in_wc_content_div.appendChild(pop_in_content_div);
	pop_in_content_section.appendChild(pop_in_wc_content_div);
	pop_in_div.appendChild(pop_in_title_div);
	pop_in_div.appendChild(pop_in_content_section);

	if (this.config.data != null) {
		for (var i = 0; i < this.config.data.length; i++) {
			var pop_in_content_item = document.createElement("li");
			pop_in_content_item.id = this.config.id + "_pop_in_content_item"
					+ i;
			pop_in_content_item.style.width = "95%";
			pop_in_content_item.style.height = "40px";
			pop_in_content_item.style.background = "#fff";
			pop_in_content_item.style.borderBottom = "1px solid #d4d4d4";
			pop_in_content_item.style.lineHeight = "40px";
			pop_in_content_item.style.paddingLeft = "5%";

			// alert(this.config.data[i].name);
			var text = document.createTextNode(this.config.data[i].name);
			pop_in_content_item.setAttribute("value", config.data[i].value);
			pop_in_content_item.appendChild(text);
			pop_in_content_div.appendChild(pop_in_content_item);
		}

	}

	this.show = function() {

		if (document.getElementById(config.id) == null) {
			document.body.appendChild(pop_bottom_div);
			this.fzclick();
		} else {
			this.fzclick();
			document.getElementById(config.id).style.display = "";
		}

	};

	this.fzclick = function() {
		var li = document.getElementsByTagName("li");
		for (var i = 0; i < li.length; i++) {
			(function(x) {
				li[x].onclick = function(e) {
					config.click.call(e, li[x].innerHTML, li[x]
							.getAttribute("value"));
				};
				li[x].addEventListener("touchstart", function(event) {
					li[x].style.backgroundColor = "#ecf0f1";
				}, null);
				li[x].addEventListener("touchend", function(event) {
					li[x].style.background = "#fff";
				}, null);
			})(i);
		}
	};

	this.hide = function() {
		document.getElementById(this.config.id).style.display = "none";
	};

}
