package modules.Component

import geb.Module

class Select2Module extends Module {
    static content = {
        input { $('.select2-choices') }
        results { $('.select2-results').find('li') }
    }
}
