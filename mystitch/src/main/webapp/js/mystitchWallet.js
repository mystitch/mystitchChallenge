$(document).ready(function() {


google.load('payments', '1.0', {
    'packages': ['sandbox_config']
  });
  function setup() {
    runDemoButton = document.getElementById("runDemoButton");
    runDemoButton.onclick = function() {
      goog.payments.inapp.buy({
        parameters: {},
        jwt: "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxNDIwNDk" +
"1MzA5NDM1MjE2ODU3MSIsImF1ZCI6Ikdvb2dsZSI" +
"sInR5cCI6Imdvb2dsZS9wYXltZW50cy9pbmFwcC9" +
"pdGVtL3YxIiwiaWF0IjoxMzU5ODkzMzQxLCJleHA" +
"iOjEzNTk4OTkzNDEsInJlcXVlc3QiOnsibmFtZSI" +
"6IlBpZWNlIG9mIENha2UiLCJkZXNjcmlwdGlvbiI" +
"6IkEgZGVsaWNpb3VzIHBpZWNlIG9mIHZpcnR1YWw" +
"gY2FrZSIsInByaWNlIjoiMTUwLjAwIiwiY3VycmV" +
"uY3lDb2RlIjoiQVVEIiwic2VsbGVyRGF0YSI6IjE" +
"wNzA3MjMxNDc1NzgzODk1MDU0In19.emLz1mUQcf" +
"qeTjmBCT3hZyKmD0F-nAi7oAghBZB_mHk",
        success: function() {window.alert("success")},
        failure: function() {window.alert("failure")}
      });
      return false;
    };
  }
  
 }