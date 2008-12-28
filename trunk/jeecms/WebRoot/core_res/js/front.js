Pn.ns('JCore','JCore.CheckCode');
/**
 * @param input
 *            ��֤�������jquery����
 * @param url
 *            ��֤���url��ַ��
 * @param top
 *            ����ƫ������
 */
JCore.CheckCode.cssClass = 'j-chkcode';
JCore.CheckCode.url = '/CheckCode.svl';
JCore.CheckCode = function(input, url, top) {
	this.input = input;
	this.url = url || JCore.CheckCode.url;
	this.top = top || 45;
	this.imgLayer = null;
	this.img = null;
	this.event = null;
	this.isShow = false;
	var o = this;
	var showImg = function() {
		if (o.imgLayer == null) {
			o.createHtml();
		}
		if (!o.isShow) {
			var d = new Date().getTime();
			o.img.attr('src', o.url + '?d=' + d);
			var offset = o.input.offset();
			o.imgLayer.show();
			o.imgLayer.css('top', offset.top - o.top + 'px');
			o.imgLayer.css('left', offset.left + 'px');
			o.isShow = true;
		}
	};
	var hideImg = function() {
		if (o.isShow) {
			o.event = setTimeout(function() {
				o.imgLayer.hide();
				o.isShow = false;
			}, 200);
		}
	};
	this.input.bind('focus', showImg);
	this.input.bind('blur', hideImg);
};
JCore.CheckCode.prototype.createHtml = function() {
	this.imgLayer = $('<div/>');
	this.img = $('<img border="0" alt="��֤�뿴�����?����ˢ����֤��"/>');
	var o = this;
	this.img.bind('click', function() {
		o.input.focus();
		if (o.event) {
			clearTimeout(o.event);
		}
		this.src = o.url + '?d=' + new Date().getTime();
	});
	this.img.appendTo(this.imgLayer);
	this.imgLayer.appendTo(document.body);
	this.imgLayer.addClass('j-chkcode');
};
/**
 * ���Ϲ���js��
 */
JCore.UpRoller = function(rid, speed, isSleep, sleepTime, rollCount, rollSpan,
		unitHight) {
	this.speed = speed;
	this.rid = rid;
	this.isSleep = isSleep;
	this.sleepTime = sleepTime;
	this.rollCount = rollCount;
	this.rollSpan = rollSpan;
	this.unitHight = unitHight;
	this.proll = $('#roll-' + rid);
	this.prollOrig = $('#roll-orig-' + rid);
	this.prollCopy = $('#roll-copy-' + rid);
	// this.prollLine = $('#p-roll-line-'+rid);
	this.sleepCount = 0;
	this.prollCopy[0].innerHTML = this.prollOrig[0].innerHTML;
	var o = this;
	this.pevent = setInterval(function() {
		o.roll.call(o)
	}, this.speed);
}
JCore.UpRoller.prototype.roll = function() {
	if (this.proll[0].scrollTop > this.prollCopy[0].offsetHeight) {
		this.proll[0].scrollTop = this.rollSpan + 1;
	} else {
		if (this.proll[0].scrollTop % (this.unitHight * this.rollCount) == 0
				&& this.sleepCount <= this.sleepTime && this.isSleep) {
			this.sleepCount++;
			if (this.sleepCount >= this.sleepTime) {
				this.sleepCount = 0;
				this.proll[0].scrollTop += this.rollSpan;
			}
		} else {
			var modCount = (this.proll[0].scrollTop + this.rollSpan)
					% (this.unitHight * this.rollCount);
			if (modCount < this.rollSpan) {
				this.proll[0].scrollTop += this.rollSpan - modCount;
			} else {
				this.proll[0].scrollTop += this.rollSpan;
			}
		}
	}
}
JCore.LeftRoller = function(rid, speed, rollSpan) {
	this.rid = rid;
	this.speed = speed;
	this.rollSpan = rollSpan;
	this.proll = $('#roll-' + rid);
	this.prollOrig = $('#roll-orig-' + rid);
	this.prollCopy = $('#roll-copy-' + rid);
	this.prollCopy[0].innerHTML = this.prollOrig[0].innerHTML;
	var o = this;
	this.pevent = setInterval(function() {
		o.roll.call(o)
	}, this.speed);
}
JCore.LeftRoller.prototype.roll = function() {
	if (this.proll[0].scrollLeft > this.prollCopy[0].offsetWidth) {
		this.proll[0].scrollLeft = this.rollSpan + 1;
	} else {
		this.proll[0].scrollLeft += this.rollSpan;
	}
}