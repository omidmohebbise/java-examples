#!/bin/bash
format_bytes() {
  local bytes=$1
  local units=("B" "KB" "MB" "GB" "TB")
  local unit=0

  while ((bytes > 1024)) && ((unit < ${#units[@]} - 1)); do
    bytes=$((bytes / 1024))
    unit=$((unit + 1))
  done

  echo "$bytes ${units[$unit]}"
}



./gradlew clean build
java -Xms2G -Xmx5G -jar ./build/libs/app-v1.jar &

# Capture the PID of the Java application running the JAR file
pid=$(pgrep -f 'java .*app-v1.jar')

# Optionally, you can print the PID
echo "PID of the Java application: $pid"
  #jcmd "$pid" GC.heap_info
  heap_summary=$(jcmd "$pid" GC.heap_info )

    # Convert the heap memory size to human-readable format
    heap_memory_size=$(format_bytes "$heap_summary")

    # Print the heap memory size
    echo "Heap Memory Size: $heap_memory_size"

# Wait for the Java application to complete (optional, if needed)
echo "End of script"
wait $pid
