<%
    String[] states = {"coahuila", "chihuahua", "colima"};
    String[] capitals = {"saltillo", "tuxtla Gutiérrez", "colima"};
    int i = 0;
    boolean findState = false;
    String stateRequested = request.getParameter("state");
    String output = "";
    for(String state : states) {
        if (state.contains(stateRequested))
            output += "[" + states[i] + " - " + capitals[i] + "], ";
    }
    if (output != "")
        out.write(output);
    else
        out.write("Not find");
%>