/**
 * Javascript functions for spaceX API program.
 */

/** 
* Reverses list of nodes.
* Given list is sorted before received by this file, reversing the list will also correctly reverse the order.
*/
function reverseList(input){
    let parent = document.getElementById(input.name); //grab top node before for-each
    for (var i = 1; i < parent.childNodes.length; i++){ //iterate each child node made in initial loop
        parent.insertBefore(parent.childNodes[i], parent.firstChild); //grabs "next" element and puts it behind the first
    }
}

/**
*  Hide and show elements of a row based on existing presence.
*/
let switchCount = 0; //manage how many elements are visible to determine header visibility
function displayToggle(input){
    document.getElementsByName(input.id).forEach(
        x => {
            if(x.style.display==="none"){
                x.style.display = "";
                switchCount++;
            } else{
                x.style.display = "none";
                switchCount--;
            } 
        }
    );
    if(switchCount < 1){ //if 0 elements are visible, hide the headers
        document.getElementsByName("headers").forEach(
        x => x.style.display="none");
    } else { //else, show the headers
        document.getElementsByName("headers").forEach(
        x => x.style.display="");
    }
}