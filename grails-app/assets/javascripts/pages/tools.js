(function ($, undefined) {


    function _initBox() {

        $(".span-left").each(function () {
            var $this = $(this);
            var type = $(this).attr("value");
            if (type === "SDM") {
                $this.addClass("sdm");
                return;
            }
            if (type === "Basic") {
                $this.addClass("basic");
                return;
            }
            else {
                $this.addClass("outcome");
            }
        });
    }

    function _clearMenu() {
        $(".btn-dropdown").parent().removeClass('open');

    }

    function _triggerButtonDropdown() {
        $(".btn-dropdown").on("click", function (e) {
            e.preventDefault();
            e.stopPropagation();

            var $this = $(this);
            var $parent = $this.parent();
            var isActive = $parent.hasClass('open');

            if (!isActive) {
                $this.trigger('focus');
                $parent.addClass('open');
                return;
            }

        });

        $(document).bind("click", _clearMenu);

    }



    function _init() {
        _initBox();
        _triggerButtonDropdown();
    }

    _init();

})(jQuery);
