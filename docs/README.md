# Duck User Guide

Duck is a friendly chatbot that manages your todo lists, deadlines and events,
and is easy to setup and use. 

## Features
There are 9 features in total.

### Create tasks
1. [Tasks without deadlines: `todo`](#todo)
2. [Tasks with deadlines: `deadline`](#deadline)
3. [Events with duration: `event`](#event)

### Manage tasks
1. [Delete task: `delete`](#delete)
2. [Mark task: `mark`](#mark)
3. [Unmark task: `unmark`](#unmark)
4. [List of tasks: `list`](#list)
5. [Search tasks: `find`](#find)
6. [Snooze deadline: `snooze`](#snooze)

## Usage
### `todo`

Creates a task with no time attribute.

Example: `todo buy groceries`

Expected outcome: Stores a task with a description "buy groceries"

```
    __________________________________________________________
    I've added this task:
    [T][ ] buy groceries
    Now you have 1 tasks in the list.
    __________________________________________________________
```

### `deadline`

Creates and stores a task with a deadline.

Example: `deadline lab assignment /by 2025-02-19 23590`

Expected outcome: Stores a task with a description "lab assignment" and deadline.

```
    __________________________________________________________
    I've added this task:
    [D][ ] lab assignment (by: 19 Feb 2025, 11:59pm)
    Now you have 1 tasks in the list.
    __________________________________________________________
```

### `event`

Creates and stores an event with a duration.

You can also use the keyword `now` or `later`  
`now` sets the time as the current time  
`later` sets the time as a random time in the future.

Example 1: `event study /from now /to later`  
Example 2: `event study /from now /to 2025-02-18 0419`

Expected outcome: Stores an event with a description "study session" and duration.

```
    __________________________________________________________
    I've added this task:
    [E][ ] study (from: 17 Feb 2025, 12:58pm to: 18 Feb 2025, 04:19am)
    Now you have 1 tasks in the list.
    __________________________________________________________
```

### `delete`

Deletes a task from your list of tasks.

Example: `delete 1`

Expected outcome: First task is deleted from the list.

```
    __________________________________________________________
    I've removed this task:
    [T][ ] buy groceries
    Now you have 0 tasks in the list.
    __________________________________________________________
```

### `mark`

Marks a task as complete.

Example: `mark 1`

Expected outcome: Marks the first task.

```
    __________________________________________________________
    Nice! I've marked this task as done:
    [T][X] buy groceries
    __________________________________________________________
```

### `unmark`

Marks a task as incomplete.

Example: `unmark 1`

Expected outcome: Removes the mark for the first task.

```
    __________________________________________________________
    Nice! I've marked this task as not done yet:
    [T][ ] buy groceries
    __________________________________________________________
```

### `list`
Shows the user the list of tasks stored.

Example: `list`

Expected outcome: Shows the list of tasks.

```
    __________________________________________________________
    Here are the tasks in your list:
    1.[T][ ] buy groceries
    2.[D][ ] lab assignment (by: 19 Feb 2025, 11:59pm)
    3.[E][ ] study (from: 17 Feb 2025, 12:58pm to: 18 Feb 2025, 04:19am)
    __________________________________________________________
```

### `find`

Searches for a task based on the user's input.

Example: `find assignment`

Expected outcome: Searches for tasks with descriptions that contain the string "assignment".

```
    __________________________________________________________
    Here are the matching tasks in your list:
    1.[D][ ] lab assignment (by: 19 Feb 2025, 11:59pm)
    __________________________________________________________
```

### `snooze`

Sets the 'by' time for a deadline task to a random future date and time

Example: `snooze 1`

Expected outcome: Randomly sets the deadline for a Deadline task to the future

```
    __________________________________________________________
    Nice. I've snoozed this task:
    1.[D][ ] lab assignment (by: 22 Feb 2025, 04:20pm)
    __________________________________________________________
```

## Command summary

| Action   | Format                                                      |
|----------|-------------------------------------------------------------|
| todo     | todo description (e.g. todo buy groceries)                  |
| deadline | deadline description /by yyyy-MM-dd HHmm                    |
| event    | event description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm |
| delete   | delete task number (e.g. delete 1)                          |
| mark     | mark task number (e.g. mark 1)                              |
| unmark   | unmark task number (e.g. unmark 1)                          |
| list     | list                                                        |
| find     | find keyword (e.g. find assignment)                         |
| snooze   | snooze deadline task (e.g. snooze 1)                        |

