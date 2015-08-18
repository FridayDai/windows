var data = {};

module.exports = {
    get: function (field) {
        return data[field];
    },

    set: function (field, val) {
        data[field] = val;

        return this;
    }
};
