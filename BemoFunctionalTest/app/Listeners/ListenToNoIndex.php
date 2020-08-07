<?php

namespace App\Listeners;

use App\Events\ChangeFileToNoIndex;
use Illuminate\Queue\InteractsWithQueue;
use Illuminate\Contracts\Queue\ShouldQueue;

class ListenToNoIndex
{
    /**
     * Create the event listener.
     *
     * @return void
     */
    public function __construct()
    {
        //
    }

    /**
     * Handle the event.
     *
     * @param  ChangeFileToNoIndex  $event
     * @return void
     */
    public function handle(ChangeFileToNoIndex $event)
    {
        //
    }
}
