const timestampToDateTimeString = (timestamp) => {
    var year = timestamp[0]
    var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
    var month = months[timestamp[1]]
    var day = timestamp[2];
    var hours = timestamp[3]
    var minutes = '0' + timestamp[4]
    return year + ' ' + month + ' ' + day + ' at ' + hours + ':' + minutes.substr(-2);
}

export {
    timestampToDateTimeString
}